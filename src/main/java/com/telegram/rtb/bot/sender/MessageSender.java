package com.telegram.rtb.bot.sender;

import com.telegram.rtb.bot.executor.MessageExecutor;
import com.telegram.rtb.model.message.MethodName;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Generic message sender.
 *
 * @author Valentyn Korniienko
 */
@Component
public class MessageSender {

    private final Map<String, MessageExecutor> botApiMethodsExecute = new ConcurrentHashMap<>();

    /**
     * Register api methods.
     *
     * @param methodName the bot api method name
     * @param executor   the message executor
     */
    public void registerApiMethod(String methodName, MessageExecutor executor) {
        botApiMethodsExecute.put(methodName, executor);
    }

    /**
     * Send message.
     *
     * @param botApiMethod     the generic bot api method
     * @param methodName       the method name enum
     * @param executorFunction the executor function
     * @param <T>              the response type from execution
     */
    public <T> void sendMessage(BotApiMethod<?> botApiMethod, MethodName methodName, Function<BotApiMethod<?>, T> executorFunction) {
        botApiMethodsExecute.get(botApiMethod.getMethod()).executeMessage(botApiMethod, methodName, executorFunction);
    }

}
