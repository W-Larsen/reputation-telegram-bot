package com.telegram.rtb.service.chat;

import com.telegram.rtb.model.domain.TelegramChat;

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
}
