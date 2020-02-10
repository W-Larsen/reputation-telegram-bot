package com.telegram.drb.command;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

/**
 * Command.
 *
 * @author Valentyn Korniienko
 */
public interface Command {

    /**
     * Execute command.
     *
     * @param user the user
     * @param chat the chat
     * @return response
     */
    String execute(User user, Chat chat);

}
