package com.telegram.rtb.security.access;

import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.chat.Chat;

/**
 * Telegram security access interface.
 *
 * @author Valentyn Korniienko
 */
public interface TelegramSecurityAccess {

    /**
     * Checks if access allowed.
     *
     * @param command the command name
     * @param chat    the chat
     * @param from    the requested user
     * @return true/false
     */
    boolean isAccessAllowed(String command, Chat chat, User from);
}
