package com.telegram.rtb.command.handler;

import com.telegram.rtb.command.Command;
import com.telegram.rtb.model.message.BotApiMethodResponse;
import com.telegram.rtb.util.MessageUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.telegram.rtb.model.message.MethodName.NO_METHOD;

/**
 * Command handler.
 *
 * @author Valenyn Korniienko
 */
@Component
@PropertySource(value = "classpath:commands.properties")
public class CommandHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandHandler.class);

    @Autowired
    private Map<String, Command> commandMap;

    @Value("${plus}")
    private List<String> plusMessages;
    @Value("${minus}")
    private List<String> minusMessages;

    @Value("${telegram.reputation.bot.username}")
    private String botUsername;

    private Map<String, List<String>> relatedMessages;
    private BotApiMethodResponse defaultBotApiMethodResponse;

    @PostConstruct
    public void init() {
        initDefaultBotApiMethodResponse();
        relatedMessages = new HashMap<>();
        relatedMessages.put("+", plusMessages);
        relatedMessages.put("-", minusMessages);
        logInfo(relatedMessages);
    }

    /**
     * Handle message.
     *
     * @param message the message.
     * @return command response
     */
    public BotApiMethodResponse handleMessage(Message message) {
        String commandText = message.getText();
        if (StringUtils.isNotEmpty(commandText)) {
            commandText = MessageUtils.trimBotUserName(commandText, botUsername);
            commandText = MessageUtils.getPossibleManagingCommand(commandText, relatedMessages);
            if (commandMap.containsKey(commandText)) {
                Command command = commandMap.get(commandText);
                return command.execute(message);
            }
        }
        return defaultBotApiMethodResponse;
    }

    private void initDefaultBotApiMethodResponse() {
        defaultBotApiMethodResponse = new BotApiMethodResponse();
        defaultBotApiMethodResponse.setBotApiMethods(Collections.emptyList());
        defaultBotApiMethodResponse.setMethodName(NO_METHOD);
    }

    private void logInfo(Map<String, List<String>> relatedMessages) {
        LOGGER.info("Related messages was uploaded. List of messages: ");
        relatedMessages.forEach((key, value) -> {
            LOGGER.info(String.format("Key: %s. Values: %s", key, value));
        });
    }

}