package com.telegram.rtb.repository.reputation.impl;

import com.telegram.rtb.model.domain.Sort;
import com.telegram.rtb.model.domain.UserReputation;
import com.telegram.rtb.repository.mapper.UserReputationMapper;
import com.telegram.rtb.repository.reputation.IUserReputationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.telegram.rtb.constants.SqlQueries.CREATE_USER_REPUTATION;
import static com.telegram.rtb.constants.SqlQueries.FIND_ALL;
import static com.telegram.rtb.constants.SqlQueries.FIND_BY_USER_ID_AND_CHAT_ID;
import static com.telegram.rtb.constants.SqlQueries.INCREASE_USER_REPUTATION;
import static com.telegram.rtb.constants.SqlQueries.REDUCE_USER_REPUTATION;
import static com.telegram.rtb.constants.SqlQueries.UPDATE_REPUTATION_VALUE;

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
                userReputation.getReputationValue(), userReputation.getUpdatedDatetime(), userReputation.getUpdatedFrom());
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
        jdbcTemplate.update(INCREASE_USER_REPUTATION, userReputation.getUpdatedDatetime(), userReputation.getUpdatedFrom(),
                userReputation.getUserId(), userReputation.getChatId());
    }

    @Override
    public void reduceUserReputation(UserReputation userReputation) {
        jdbcTemplate.update(REDUCE_USER_REPUTATION, userReputation.getUpdatedDatetime(), userReputation.getUpdatedFrom(),
                userReputation.getUserId(), userReputation.getChatId());
    }

    @Override
    public List<UserReputation> findAll(Sort sort) {
        return jdbcTemplate.query(FIND_ALL + sort.getValue(),  new UserReputationMapper());
    }

    @Override
    public void updateUserReputation(Integer userId, Long chatId, Integer reputationValue) {
        jdbcTemplate.update(UPDATE_REPUTATION_VALUE, reputationValue, userId, chatId);
    }
}
