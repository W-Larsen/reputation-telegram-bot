package com.telegram.drb.command;

import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * Abstract class implemented Command.
 *
 * @author Valentyn Korniienko
 */
public abstract class AbstractCommand implements Command {

    @Value("${dawg.reputation.bot.username}")
    protected String botUserName;

    /**
     * Create default message response.
     *
     * @param chatId       the chat id
     * @param responseText the response text
     * @return send message response class
     */
    protected SendMessage createDefaultMessageResponse(long chatId, String responseText) {
        SendMessage response = new SendMessage();
        response.setChatId(chatId);
        response.setText(responseText);
        return response;
    }
}
