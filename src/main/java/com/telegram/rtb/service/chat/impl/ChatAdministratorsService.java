package com.telegram.rtb.service.chat.impl;

import com.telegram.rtb.model.message.ChatAdministrators;
import com.telegram.rtb.service.chat.IChatAdministratorsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ChatAdministratorsService implements IChatAdministratorsService {

    @Autowired
    private ChatAdministrators chatAdministrators;

    @Override
    public void populateChatAdministrators(String chatId, List<ChatMember> executedChatAdministrators) {
        Map<String, List<ChatMember>> chatAdministratorsByChatId = chatAdministrators.getChatAdministratorsByChatId();
        chatAdministratorsByChatId.put(chatId, executedChatAdministrators);
    }
}
