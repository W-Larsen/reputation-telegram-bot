package com.telegram.rtb.bot.executor;

import com.telegram.rtb.bot.sender.MessageSender;
import com.telegram.rtb.model.message.MethodName;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.function.Function;

/**
 * Generic message executor.
 */
public interface MessageExecutor {

    /**
     * Gets api bot method name.
     *
     * @return method name
     */
    String getMethodName();

    /**
     * Execute method.
     *
     * @param botApiMethod     the bot api method
     * @param methodName       the method name enum
     * @param executorFunction the executor function
     */
    void executeMessage(BotApiMethod<?> botApiMethod, MethodName methodName, Function<BotApiMethod<?>, Message> executorFunction);

    /**
     * This method will be automatically executed in each implementation.
     *
     * @param sender the generic message sender
     */
    @Autowired
    default void registerMethod(MessageSender sender) {
        sender.registerApiMethod(getMethodName(), this);
    }

}
