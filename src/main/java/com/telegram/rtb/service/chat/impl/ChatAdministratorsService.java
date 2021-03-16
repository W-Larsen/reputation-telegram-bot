package com.telegram.rtb.service.chat.impl;

import com.telegram.rtb.model.message.ChatAdministrators;
import com.telegram.rtb.service.chat.IChatAdministratorsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.ChatMember;

import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class ChatAdministratorsService implements IChatAdministratorsService {

    @Autowired
    private ChatAdministrators chatAdministrators;

    @Override
    public void populateChatAdministrators(String chatId, List<ChatMember> executedChatAdministrators) {
        log.info("Trying to populate administrators for chat id '{}' ...", chatId);
        Map<String, List<ChatMember>> chatAdministratorsByChatId = chatAdministrators.getChatAdministratorsByChatId();
        chatAdministratorsByChatId.put(chatId, executedChatAdministrators);
        log.info("Chat administrators were successfully populated in size {}", executedChatAdministrators.size());
    }
}
