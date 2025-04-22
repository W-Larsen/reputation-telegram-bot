package com.telegram.rtb.bot.executor;

import com.telegram.rtb.exception.TelegramApiBadRequestException;
import com.telegram.rtb.model.message.MethodName;
import com.telegram.rtb.service.chat.IChatAdministratorsService;
import com.telegram.rtb.service.chat.IChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatAdministrators;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Get chat administrators executor.
 *
 * @author Valentyn Korniienko
 */
@Component
@Slf4j
public class GetChatAdministratorsExecutor implements MessageExecutor {

    @Autowired
    private IChatAdministratorsService chatAdministratorsService;
    @Autowired
    private IChatService chatService;


    @Override
    public String getMethodName() {
        return "getChatAdministrators";
    }

    @Override
    public <T> void executeMessage(BotApiMethod<?> botApiMethod, MethodName methodName, Function<BotApiMethod<?>, T> executorFunction) {
        log.info("Populating administrators...");
        chatService.getAllChats().forEach(telegramChat ->
                applyPopulatingChatAdministrators(botApiMethod, executorFunction, telegramChat.getChatId().toString()));
    }

    @SuppressWarnings("unchecked")
    private <T> void applyPopulatingChatAdministrators(BotApiMethod<?> botApiMethod, Function<BotApiMethod<?>, T> executorFunction, String chatId) {
        log.info("Trying to populate administrators for chat id '{}' ...", chatId);
        try {
            List<ChatMember> chatMembers = (ArrayList<ChatMember>) executorFunction.apply(populateChatId(botApiMethod, chatId));
            chatAdministratorsService.populateChatAdministrators(chatId, chatMembers);
            log.info("Chat administrators were successfully populated in size {}", chatMembers.size());
        } catch (TelegramApiBadRequestException e) {
            log.error("Error getting chat administrators, skipping chatId {}", chatId);
        }
    }

    private GetChatAdministrators populateChatId(BotApiMethod<?> botApiMethod, String chatId) {
        GetChatAdministrators getChatAdministrators = (GetChatAdministrators) botApiMethod;
        getChatAdministrators.setChatId(chatId);
        return getChatAdministrators;
    }
}
