package com.telegram.rtb.util;

import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatAdministrators;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

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
    public static SendMessage createSendMessage(Message message, String responseText) {
        SendMessage messageResponse = createDefaultSendMessageResponse(message.getChatId(), responseText);
        messageResponse.setParseMode(MARKDOWN_V2.getValue());
        return messageResponse;
    }

    private static SendMessage createDefaultSendMessageResponse(Long chatId, String responseText) {
        return SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text(responseText)
                .build();
    }

}
