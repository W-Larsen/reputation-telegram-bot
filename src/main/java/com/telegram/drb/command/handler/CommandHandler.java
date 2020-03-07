package com.telegram.drb.command.handler;

import com.telegram.drb.command.Command;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

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

    @PostConstruct
    public void init() {
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
    public BotApiMethod<?> handleMessage(Message message) {
        String commandText = message.getText();
        String possibleCommand = getPossibleCommand(commandText);
        commandText = StringUtils.isEmpty(possibleCommand) ? commandText : possibleCommand;
        if (commandMap.containsKey(commandText)) {
            Command command = commandMap.get(commandText);
            return command.execute(message);
        }
        return null;
    }

    private String getPossibleCommand(String commandText) {
        return relatedMessages.entrySet().stream()
            .filter(entry -> entry.getValue().contains(commandText))
            .map(Map.Entry::getKey)
            .findFirst()
            .orElse(StringUtils.EMPTY);
    }

}
