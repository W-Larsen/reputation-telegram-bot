package com.telegram.rtb.util;

import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatAdministrators;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.message.Message;

import static com.telegram.rtb.model.domain.ParseMode.MARKDOWN_V2;

/**
 * Bot api method creator.
 */
public final class BotApiMethodCreator {

    private BotApiMethodCreator() {
        throw new UnsupportedOperationException();
    }

    /**
     * Create GetChatAdministrators.
     *
     * @param chatId the chat id
     * @return GetChatAdministrators entity
     */
    public static GetChatAdministrators createGetChatAdministrators(String chatId) {
        return GetChatAdministrators.builder()
                .chatId(chatId)
                .build();
    }

    /**
     * Create SendMessage with MarkdownV2 parse mode.
     *
     * @param message      the message.
     * @param responseText the response text
     * @return SendMessage entity
     */
    public static SendMessage createSendMessageWithMarkDown_V2(Message message, String responseText) {
        SendMessage messageResponse = createDefaultSendMessage(message, responseText);
        messageResponse.setParseMode(MARKDOWN_V2.getValue());
        return messageResponse;
    }

    /**
     * Create default SendMessage.
     *
     * @param message      the message
     * @param responseText the response text
     * @return SendMessage entity
     */
    public static SendMessage createDefaultSendMessage(Message message, String responseText) {
        return SendMessage.builder()
                .chatId(String.valueOf(message.getChatId()))
                .text(responseText)
                .build();
    }

}
