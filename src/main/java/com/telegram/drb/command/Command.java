package com.telegram.drb.command;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
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
     * @param message the message
     * @return response
     */
    String execute(Message message);

}
