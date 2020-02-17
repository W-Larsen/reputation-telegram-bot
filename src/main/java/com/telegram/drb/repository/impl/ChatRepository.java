package com.telegram.drb.repository.impl;

import com.telegram.drb.model.domain.TelegramChat;
import com.telegram.drb.repository.IChatRepository;
import com.telegram.drb.repository.mapper.TelegramChatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import static com.telegram.drb.constants.SqlQueries.ADD_TELEGRAM_CHAT;
import static com.telegram.drb.constants.SqlQueries.FIND_CHAT_BY_ID;

/**
 * Telegram chat repository implementation.
 *
 * @author Valentyn Korniienko
 */
public class ChatRepository implements IChatRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public TelegramChat addTelegramChat(TelegramChat telegramChat) {
        jdbcTemplate.update(ADD_TELEGRAM_CHAT, telegramChat.getChatId(), telegramChat.getChatName());
        return telegramChat;
    }

    @Override
    public TelegramChat findChatById(Long telegramChatId) {
        try {
            return jdbcTemplate.queryForObject(FIND_CHAT_BY_ID, new Object[]{telegramChatId}, new TelegramChatMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
