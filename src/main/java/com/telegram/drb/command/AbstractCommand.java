package com.telegram.drb.command;

import com.telegram.drb.model.message.BotApiMethodResponse;
import com.telegram.drb.model.message.MethodName;
import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;

import java.util.List;

/**
 * Abstract class implemented Command.
 *
 * @author Valentyn Korniienko
 */
public abstract class AbstractCommand implements Command {

    /**
     * Create Bot api method response.
     *
     * @param botApiMethods the bot api methods
     * @param methodName    the method name
     * @return bot api method response
     */
    protected BotApiMethodResponse createBotApiMethodResponse(List<BotApiMethod<?>> botApiMethods, MethodName methodName) {
        BotApiMethodResponse response = new BotApiMethodResponse();
        response.setBotApiMethods(botApiMethods);
        response.setMethodName(methodName);
        return response;
    }

    /**
     * Create default SendMessage response.
     *
     * @param chatId       the chat id
     * @param responseText the response text
     * @return SendMessage response class
     */
    protected SendMessage createDefaultSendMessageResponse(long chatId, String responseText) {
        SendMessage response = new SendMessage();
        response.setChatId(chatId);
        response.setText(responseText);
        return response;
    }

    /**
     * Create default DeleteMessage response.
     *
     * @param chatId    the chat id
     * @param messageId the message id
     * @return DeleteMessage response class
     */
    protected DeleteMessage createDefaultDeleteMessageResponse(long chatId, Integer messageId) {
        DeleteMessage response = new DeleteMessage();
        response.setChatId(chatId);
        response.setMessageId(messageId);
        return response;
    }

}
