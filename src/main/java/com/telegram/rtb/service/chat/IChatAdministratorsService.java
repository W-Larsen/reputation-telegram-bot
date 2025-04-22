package com.telegram.rtb.service.chat;


import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;

import java.util.List;

/**
 * Chat administrators service interface.
 *
 * @author Valentyn Korniienko
 */
public interface IChatAdministratorsService {

    /**
     * Populate chat administrators by chat id.
     *
     * @param chatId                     chat id
     * @param executedChatAdministrators executed chat administrators
     */
    void populateChatAdministrators(String chatId, List<ChatMember> executedChatAdministrators);
}
