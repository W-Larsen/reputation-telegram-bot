package com.telegram.rtb.repository.user.impl;

import com.telegram.rtb.model.domain.TelegramUser;
import com.telegram.rtb.repository.user.IUserRepository;
import com.telegram.rtb.repository.mapper.TelegramUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import static com.telegram.rtb.constants.SqlQueries.ADD_TELEGRAM_USER;
import static com.telegram.rtb.constants.SqlQueries.FIND_USER_BY_ID;

/**
 * User repository implementation.
 *
 * @author Valentyn Korniienko
 */
@Repository
public class UserRepository implements IUserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public TelegramUser addTelegramUser(TelegramUser telegramUser) {
        jdbcTemplate.update(ADD_TELEGRAM_USER, telegramUser.getUserId(), telegramUser.getUserName(),
                telegramUser.getFirstName(), telegramUser.getLastName());
        return telegramUser;
    }

    @Override
    public TelegramUser findUserById(Integer userId) {
        try {
            return jdbcTemplate.queryForObject(FIND_USER_BY_ID, new Object[]{userId}, new TelegramUserMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
