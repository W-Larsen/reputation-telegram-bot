package com.telegram.drb.repository.mapper;

import com.telegram.drb.model.domain.TelegramUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Telegram user mapper.
 *
 * @author Valentyn Korniienko
 */
public class TelegramUserMapper implements RowMapper<TelegramUser> {

    @Override
    public TelegramUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        TelegramUser telegramUser = new TelegramUser();
        telegramUser.setUserId(rs.getInt("user_id"));
        telegramUser.setUserName(rs.getString("user_name"));
        telegramUser.setFirstName(rs.getString("first_name"));
        telegramUser.setLastName(rs.getString("last_name"));
        return telegramUser;
    }

}
