package com.telegram.rtb.bot.executor;

import com.telegram.rtb.model.message.ChatAdministrators;
import com.telegram.rtb.model.message.MethodName;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatAdministrators;
import org.telegram.telegrambots.meta.api.objects.ChatMember;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    private ChatAdministrators chatAdministrators;

    @Override
    public String getMethodName() {
        return "getChatAdministrators";
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void executeMessage(BotApiMethod<?> botApiMethod, MethodName methodName, Function<BotApiMethod<?>, T> executorFunction) {
        GetChatAdministrators getChatAdministrators = (GetChatAdministrators) botApiMethod;
        List<ChatMember> chatMembers = (ArrayList<ChatMember>) executorFunction.apply(getChatAdministrators);
        populateChatAdministrators(getChatAdministrators.getChatId(), chatMembers);
    }

    private void populateChatAdministrators(String chatId, List<ChatMember> chatMembers) {
        log.info("Trying to populate administrators for chat id '{}' ...", chatId);
        Map<String, List<ChatMember>> chatAdministratorsByChatId = chatAdministrators.getChatAdministratorsByChatId();
        chatAdministratorsByChatId.putIfAbsent(chatId, chatMembers);
        log.info("Chat administrators were successfully populated.");
    }
}
