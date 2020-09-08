package com.telegram.rtb.repository.chat;

import com.telegram.rtb.model.domain.TelegramChat;

/**
 * Chat repository interface.
 *
 * @author Valentyn Korniienko
 */
public interface IChatRepository {

    /**
     * Add telegram chat.
     *
     * @param telegramChat the telegram chat
     * @return created telegram chat
     */
    TelegramChat addTelegramChat(TelegramChat telegramChat);

    /**
     * Find chat by id.
     *
     * @param telegramChatId the telegram chat id
     * @return telegram chat
     */
    TelegramChat findChatById(Long telegramChatId);

}
