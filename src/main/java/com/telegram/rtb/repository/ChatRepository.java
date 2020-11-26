package com.telegram.rtb.repository;

import com.telegram.rtb.model.domain.TelegramChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Chat repository interface.
 *
 * @author Valentyn Korniienko
 */
@Repository
public interface ChatRepository extends JpaRepository<TelegramChat, Long> {

    /**
     * Find chat by id.
     *
     * @param telegramChatId the telegram chat id
     * @return telegram chat
     */
    @Query(value = "SELECT chat from TelegramChat chat WHERE chat.chatId = :telegramChatId")
    TelegramChat findChatById(Long telegramChatId);

}
