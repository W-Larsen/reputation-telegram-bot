package com.telegram.drb.service.chat.impl;

import com.telegram.drb.model.domain.TelegramChat;
import com.telegram.drb.repository.chat.IChatRepository;
import com.telegram.drb.service.chat.IChatService;
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
    private IChatRepository chatRepository;

    @Override
    public void addTelegramChat(TelegramChat telegramChat) {
        if (chatRepository.findChatById(telegramChat.getChatId()) == null) {
            chatRepository.addTelegramChat(telegramChat);
        }
    }
}
