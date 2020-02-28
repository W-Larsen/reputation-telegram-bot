package com.telegram.drb.command.handler;

import com.telegram.drb.command.Command;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Map;

/**
 * Command handler.
 *
 * @author Valenyn Korniienko
 */
@Component
public class CommandHandler {

    @Autowired
    private Map<String, Command> commandMap;

    /**
     * Handle message.
     *
     * @param message the message.
     * @return command response
     */
    public String handleMessage(Message message) {
        String commandText = message.getText();
        if (commandMap.containsKey(commandText)) {
            Command command = commandMap.get(commandText);
            return command.execute(message);
        }
        return StringUtils.EMPTY;
    }

}
