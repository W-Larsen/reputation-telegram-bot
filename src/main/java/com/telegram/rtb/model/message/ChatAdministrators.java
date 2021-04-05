package com.telegram.rtb.model.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.ChatMember;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Chat administrators entity.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatAdministrators {
    private Map<String, List<ChatMember>> chatAdministratorsByChatId;

    /**
     * Gets administrator by chat id and user id.
     *
     * @param chatId the chat id
     * @param userId the user id
     * @return chat administrator
     */
    public Optional<ChatMember> getAdministratorByChatIdAndUserId(String chatId, Long userId) {
        if (chatAdministratorsByChatId.containsKey(chatId)) {
            return chatAdministratorsByChatId.get(chatId).stream()
                    .filter(chatMember -> chatMember.getUser().getId().equals(userId))
                    .findAny();
        }
        return Optional.empty();
    }
}
