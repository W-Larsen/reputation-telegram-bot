package com.telegram.rtb.service.chat.impl;

import com.telegram.rtb.model.domain.TelegramChat;
import com.telegram.rtb.repository.ChatRepository;
import com.telegram.rtb.service.chat.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Chat service implementation.
 *
 * @author Valentyn Korniienko
 */
@Service
public class ChatService implements IChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public void addTelegramChat(TelegramChat telegramChat) {
        if (chatRepository.findChatById(telegramChat.getChatId()) == null) {
            chatRepository.save(telegramChat);
        }
    }
}
