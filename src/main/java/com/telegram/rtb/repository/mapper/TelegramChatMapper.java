package com.telegram.rtb.repository.mapper;

import com.telegram.rtb.model.domain.TelegramChat;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Telegram chat mapper.
 *
 * @author Valentyn Korniienko
 */
public class TelegramChatMapper implements RowMapper<TelegramChat> {

    @Override
    public TelegramChat mapRow(ResultSet rs, int rowNum) throws SQLException {
        TelegramChat telegramChat = new TelegramChat();
        telegramChat.setChatId(rs.getLong("chat_id"));
        telegramChat.setChatName(rs.getString("chat_name"));
        return telegramChat;
    }

}
