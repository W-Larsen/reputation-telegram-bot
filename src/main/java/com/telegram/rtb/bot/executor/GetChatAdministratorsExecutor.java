package com.telegram.rtb.bot.executor;

import com.telegram.rtb.model.message.MethodName;
import com.telegram.rtb.service.chat.IChatAdministratorsService;
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
public class GetChatAdministratorsExecutor implements MessageExecutor {

    @Autowired
    private IChatAdministratorsService chatAdministratorsService;

    @Override
    public String getMethodName() {
        return "getChatAdministrators";
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void executeMessage(BotApiMethod<?> botApiMethod, MethodName methodName, Function<BotApiMethod<?>, T> executorFunction) {
        GetChatAdministrators getChatAdministrators = (GetChatAdministrators) botApiMethod;
        List<ChatMember> chatMembers = (ArrayList<ChatMember>) executorFunction.apply(getChatAdministrators);
        chatAdministratorsService.populateChatAdministrators(getChatAdministrators.getChatId(), chatMembers);
    }
}
