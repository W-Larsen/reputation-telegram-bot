package com.telegram.rtb.bot.executor;

import com.telegram.rtb.model.cache.MessageCache;
import com.telegram.rtb.model.message.MethodName;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
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
@Log4j2
public class SendMessageExecutor implements MessageExecutor {

    @Autowired
    private MessageCache messageCache;

    @Override
    public String getMethodName() {
        return "sendmessage";
    }

    @Override
    public <T> void executeMessage(BotApiMethod<?> botApiMethod, MethodName methodName, Function<BotApiMethod<?>, T> executorFunction) {
        SendMessage sendMessageResponse = (SendMessage) botApiMethod;
        if (StringUtils.isNotEmpty(sendMessageResponse.getText())) {
            Message message = (Message) executorFunction.apply(sendMessageResponse);
            if (methodName.equals(MANAGE_REPUTATION)) {
                saveMessageToCache(message);
            }
            log.info("Sent message \"{}\" to {} chat ", sendMessageResponse.getText(), sendMessageResponse.getChatId());
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
