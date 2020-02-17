package com.telegram.drb.repository.impl;

import com.telegram.drb.model.domain.UserReputation;
import com.telegram.drb.repository.IUserReputationRepository;
import com.telegram.drb.repository.mapper.UserReputationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import static com.telegram.drb.constants.SqlQueries.CREATE_USER_REPUTATION;
import static com.telegram.drb.constants.SqlQueries.FIND_BY_USER_ID_AND_CHAT_ID;
import static com.telegram.drb.constants.SqlQueries.INCREASE_USER_REPUTATION;
import static com.telegram.drb.constants.SqlQueries.REDUCE_USER_REPUTATION;

/**
 * User reputation repository implementation.
 *
 * @author Valentyn Korniienko
 */
@Repository
public class UserReputationRepository implements IUserReputationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserReputation createUserReputation(UserReputation userReputation) {
        jdbcTemplate.update(CREATE_USER_REPUTATION, userReputation.getUserId(), userReputation.getChatId(),
                userReputation.getReputationValue());
        return userReputation;
    }

    @Override
    public UserReputation findByUserIdAndChatId(Integer userId, Long chatId) {
        try {
            return jdbcTemplate.queryForObject(FIND_BY_USER_ID_AND_CHAT_ID, new Object[]{userId, chatId}, new UserReputationMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void increaseUserReputation(UserReputation userReputation) {
        jdbcTemplate.update(INCREASE_USER_REPUTATION, userReputation.getUserId(), userReputation.getChatId());
    }

    @Override
    public void reduceUserReputation(UserReputation userReputation) {
        jdbcTemplate.update(REDUCE_USER_REPUTATION, userReputation.getUserId(), userReputation.getChatId());
    }

}
