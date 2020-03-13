package com.telegram.drb.command.handler;

import com.telegram.drb.command.Command;
import com.telegram.drb.model.message.BotApiMethodResponse;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    private Map<String, Command> commandMap;

    @Value("${+}")
    private String plusMessages;
    @Value("${-}")
    private String minusMessages;

    private Map<String, List<String>> relatedMessages;
    private BotApiMethodResponse defaultBotApiMethodResponse;

    @PostConstruct
    public void init() {
        initDefaultBotApiMethodResponse();
        relatedMessages = new HashMap<>();
        relatedMessages.put("+", Arrays.asList(plusMessages.split(",")));
        relatedMessages.put("-", Arrays.asList(minusMessages.split(",")));
    }

    /**
     * Handle message.
     *
     * @param message the message.
     * @return command response
     */
    public BotApiMethodResponse handleMessage(Message message) {
        String commandText = message.getText();
        String possibleCommand = getPossibleCommand(commandText);
        commandText = StringUtils.isEmpty(possibleCommand) ? commandText : possibleCommand;
        if (commandMap.containsKey(commandText)) {
            Command command = commandMap.get(commandText);
            return command.execute(message);
        }
        return defaultBotApiMethodResponse;
    }

    private String getPossibleCommand(String commandText) {
        return relatedMessages.entrySet().stream()
                .filter(entry -> entry.getValue().contains(commandText))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(StringUtils.EMPTY);
    }

    private void initDefaultBotApiMethodResponse() {
        defaultBotApiMethodResponse = new BotApiMethodResponse();
        defaultBotApiMethodResponse.setBotApiMethods(Collections.emptyList());
        defaultBotApiMethodResponse.setMethodName(NO_METHOD);
    }

}
