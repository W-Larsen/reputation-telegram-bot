package com.telegram.rtb.command;

import com.telegram.rtb.model.message.BotApiMethodResponse;
import com.telegram.rtb.model.message.MethodName;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;
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
        return BotApiMethodResponse.builder()
                .botApiMethods(botApiMethods)
                .methodName(methodName)
                .build();
    }

    /**
     * Create default SendMessage response.
     *
     * @param chatId       the chat id
     * @param responseText the response text
     * @return SendMessage response class
     */
    protected SendMessage createDefaultSendMessageResponse(Long chatId, String responseText) {
        return SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text(responseText)
                .build();
    }

    /**
     * Create default DeleteMessage response.
     *
     * @param chatId    the chat id
     * @param messageId the message id
     * @return DeleteMessage response class
     */
    protected DeleteMessage createDefaultDeleteMessageResponse(Long chatId, Integer messageId) {
        return DeleteMessage.builder()
                .chatId(String.valueOf(chatId))
                .messageId(messageId)
                .build();
    }

}
