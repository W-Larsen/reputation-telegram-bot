package com.telegram.rtb.bot.executor;

import com.telegram.rtb.model.message.MethodName;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;

import java.util.function.Function;

/**
 * Delete message executor.
 *
 * @author Valentyn Korniienko
 */
@Component
@Log4j2
public class DeleteMessageExecutor implements MessageExecutor {

    @Override
    public String getMethodName() {
        return "deleteMessage";
    }

    @Override
    public <T> void executeMessage(BotApiMethod<?> botApiMethod, MethodName methodName, Function<BotApiMethod<?>, T> executorFunction) {
        DeleteMessage deleteMessageResponse = (DeleteMessage) botApiMethod;
        if (deleteMessageResponse.getMessageId() != null) {
            executorFunction.apply(deleteMessageResponse);
            log.info("Message {} was successfully deleted", deleteMessageResponse.getMessageId());
        }
    }
}
