package com.telegram.rtb.service.chat;

import com.telegram.rtb.model.domain.TelegramChat;

import java.util.List;

/**
 * Chat service interface.
 *
 * @author Valentyn Korniienko
 */
public interface IChatService {

    /**
     * Add telegram chat.
     *
     * @param telegramChat the telegram chat
     */
    void addTelegramChat(TelegramChat telegramChat);

    /**
     * Get telegram chat by chat id.
     *
     * @param chatId the chat id
     * @return telegram chat
     */
    TelegramChat getTelegramChatByChatId(Long chatId);

    /**
     * Get all telegram chats.
     *
     * @return list of telegram chats
     */
    List<TelegramChat> getAllChats();
}
