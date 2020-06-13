package com.telegram.drb.command.handler;

import com.telegram.drb.command.Command;
import com.telegram.drb.model.message.BotApiMethodResponse;
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

import static com.telegram.drb.model.message.MethodName.NO_METHOD;

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
    private String plusMessages;
    @Value("${minus}")
    private String minusMessages;

    private Map<String, List<String>> relatedMessages;
    private BotApiMethodResponse defaultBotApiMethodResponse;

    @PostConstruct
    public void init() {
        initDefaultBotApiMethodResponse();
        relatedMessages = new HashMap<>();
        relatedMessages.put("+", Arrays.asList(plusMessages.split(",")));
        relatedMessages.put("-", Arrays.asList(minusMessages.split(",")));
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
            String possibleCommand = getPossibleCommand(commandText);
            commandText = StringUtils.isEmpty(possibleCommand) ? commandText : possibleCommand;
            if (commandMap.containsKey(commandText)) {
                Command command = commandMap.get(commandText);
                return command.execute(message);
            }
        }
        return defaultBotApiMethodResponse;
    }

    private String getPossibleCommand(String commandText) {
        return relatedMessages.entrySet().stream()
                .filter(entry -> entry.getValue().stream().anyMatch(commandText::equalsIgnoreCase))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(StringUtils.EMPTY);
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
