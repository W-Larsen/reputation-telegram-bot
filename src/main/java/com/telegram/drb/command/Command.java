package com.telegram.drb.command;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

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
    BotApiMethod<Message> execute(Message message);

}
