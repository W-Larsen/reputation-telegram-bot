package com.telegram.rtb.bot.executors;

import com.telegram.rtb.bot.TelegramReputationBot;
import com.telegram.rtb.model.message.MethodName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.function.Function;

/**
 * Delete message executor.
 *
 * @author Valentyn Korniienko
 */
@Component
public class DeleteMessageExecutor extends TelegramReputationBot implements MessageExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendMessageExecutor.class);

    @Override
    public String getMethodName() {
        return "deleteMessage";
    }

    @Override
    public void executeMessage(BotApiMethod<?> botApiMethod, MethodName methodName, Function<BotApiMethod<?>, Message> executorFunction) {
        DeleteMessage deleteMessageResponse = (DeleteMessage) botApiMethod;
        if (deleteMessageResponse.getMessageId() != null) {
            executorFunction.apply(deleteMessageResponse);
            LOGGER.info("Message {} was successfully deleted", deleteMessageResponse.getMessageId());
        }
    }
}
