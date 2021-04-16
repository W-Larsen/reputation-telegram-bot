package com.telegram.rtb.service.chat.impl;

import com.telegram.rtb.model.domain.TelegramChat;
import com.telegram.rtb.repository.ChatRepository;
import com.telegram.rtb.service.chat.IChatService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Chat service implementation.
 *
 * @author Valentyn Korniienko
 */
@Service
@Log4j2
public class ChatService implements IChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public void addTelegramChat(TelegramChat telegramChat) {
        if (chatRepository.findChatById(telegramChat.getChatId()) == null) {
            chatRepository.save(telegramChat);
        }
    }

    @Override
    public TelegramChat getTelegramChatByChatId(Long chatId) {
        return chatRepository.findChatById(chatId);
    }

    @Override
    public List<TelegramChat> getAllChats() {
        return chatRepository.findAll();
    }
}