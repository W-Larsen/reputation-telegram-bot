package com.telegram.rtb.bot.executor;

import com.telegram.rtb.model.message.MethodName;
import com.telegram.rtb.service.chat.IChatAdministratorsService;
import com.telegram.rtb.service.chat.IChatService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatAdministrators;
import org.telegram.telegrambots.meta.api.objects.ChatMember;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Get chat administrators executor.
 *
 * @author Valentyn Korniienko
 */
@Component
@Log4j2
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
    @SuppressWarnings("unchecked")
    public <T> void executeMessage(BotApiMethod<?> botApiMethod, MethodName methodName, Function<BotApiMethod<?>, T> executorFunction) {
        chatService.getAllChats().forEach(telegramChat -> {
            String chatId = telegramChat.getChatId().toString();
            log.info("Trying to populate administrators for chat id '{}' ...", chatId);

            List<ChatMember> chatMembers = (ArrayList<ChatMember>) executorFunction.apply(populateChatId(botApiMethod, chatId));
            chatAdministratorsService.populateChatAdministrators(chatId, chatMembers);

            log.info("Chat administrators were successfully populated in size {}", chatMembers.size());
        });
    }

    private GetChatAdministrators populateChatId(BotApiMethod<?> botApiMethod, String chatId) {
        GetChatAdministrators getChatAdministrators = (GetChatAdministrators) botApiMethod;
        getChatAdministrators.setChatId(chatId);
        return getChatAdministrators;
    }
}
