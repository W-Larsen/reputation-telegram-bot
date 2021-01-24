package com.telegram.rtb.bot.executor;

import com.telegram.rtb.model.message.MethodName;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.function.Function;

import static com.telegram.rtb.model.message.MethodName.MANAGE_REPUTATION;

/**
 * Send message executor.
 *
 * @author Valentyn Korniienko
 */
@Component
public class SendMessageExecutor implements MessageExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendMessageExecutor.class);

    @Autowired
    private Map<Long, Queue<Message>> messageCache;

    @Override
    public String getMethodName() {
        return "sendmessage";
    }

    @Override
    public void executeMessage(BotApiMethod<?> botApiMethod, MethodName methodName, Function<BotApiMethod<?>, Message> executorFunction) {
        SendMessage sendMessageResponse = (SendMessage) botApiMethod;
        if (StringUtils.isNotEmpty(sendMessageResponse.getText())) {
            Message message = executorFunction.apply(sendMessageResponse);
            if (methodName.equals(MANAGE_REPUTATION)) {
                saveMessageToCache(message);
            }
            LOGGER.info("Sent message \"{}\" to {} chat ", sendMessageResponse.getText(), sendMessageResponse.getChatId());
        }
    }

    private void saveMessageToCache(Message message) {
        if (messageCache.containsKey(message.getChatId())) {
            addMessageToExistenceQueue(message);
        } else {
            addMessageToTheNewQueue(message);
        }
    }

    private void addMessageToExistenceQueue(Message message) {
        Queue<Message> messageQueue = messageCache.get(message.getChatId());
        messageQueue.add(message);
    }

    private void addMessageToTheNewQueue(Message message) {
        Queue<Message> queue = new ArrayDeque<>();
        queue.add(message);
        messageCache.put(message.getChatId(), queue);
    }

}
