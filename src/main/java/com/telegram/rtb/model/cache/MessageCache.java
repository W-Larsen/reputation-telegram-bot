package com.telegram.rtb.model.cache;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Map;
import java.util.Queue;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageCache {
    private Map<Long, Queue<Message>> messageCache;

    /**
     * Is contains key.
     *
     * @param chatId the chat id
     * @return true if yes, false - otherwise
     */
    public boolean containsKey(Long chatId) {
        return messageCache.containsKey(chatId);
    }

    /**
     * Get queue of messages.
     *
     * @param chatId the chat id
     * @return queue of messages
     */
    public Queue<Message> get(Long chatId) {
        return messageCache.get(chatId);
    }

    /**
     * Put queue of messages by chat id.
     *
     * @param chatId the chat id
     * @param queue  the queue
     */
    public void put(Long chatId, Queue<Message> queue) {
        messageCache.put(chatId, queue);
    }

    /**
     * Is empty cache.
     *
     * @return true if yes, false - otherwise
     */
    public boolean isEmpty() {
        return CollectionUtils.isEmpty(messageCache);
    }
}
