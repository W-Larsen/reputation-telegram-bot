package com.telegram.rtb.command.handler;

import com.telegram.rtb.command.Command;
import com.telegram.rtb.command.PermissionDeniedCommand;
import com.telegram.rtb.security.access.TelegramSecurityAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.chat.Chat;

import java.util.Map;

/**
 * Command extractor.
 *
 * @author Valentyn Korniienko
 */
@Component
public class CommandExtractor {

    @Autowired
    private TelegramSecurityAccess securityAccess;
    @Autowired
    private Map<String, Command> commandMap;

    /**
     * Get possible command by command name.
     *
     * @param commandText the command text
     * @param chat        the chat
     * @param from        the requested user
     * @return possible command, or PermissionDeniedCommand in case when requested user does not have enough rights,
     * or 'null' if command map does not contains requested command name.
     */
    public Command getCommand(String commandText, Chat chat, User from) {
        if (commandMap.containsKey(commandText)) {
            if (securityAccess.isAccessAllowed(commandText, chat, from)) {
                return commandMap.get(commandText);
            }
            return new PermissionDeniedCommand();
        }
        return null;
    }
}
