package com.telegram.drb.command;

import com.telegram.drb.model.message.BotApiMethodResponse;
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
     * @return bot api method response
     */
    BotApiMethodResponse execute(Message message);

}
