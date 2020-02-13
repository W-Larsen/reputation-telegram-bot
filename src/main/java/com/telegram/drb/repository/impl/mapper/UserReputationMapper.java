package com.telegram.drb.repository.impl.mapper;

import com.telegram.drb.model.domain.UserReputation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User reputation mapper.
 *
 * @author Valentyn Korniienko
 */
public class UserReputationMapper implements RowMapper<UserReputation> {

    @Override
    public UserReputation mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserReputation userReputation = new UserReputation();
        userReputation.setUserId(rs.getInt("user_id"));
        userReputation.setChatId(rs.getInt("chat_id"));
        userReputation.setReputationValue(rs.getInt("reputation_value"));
        return userReputation;
    }

}
