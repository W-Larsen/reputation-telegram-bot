package com.telegram.rtb.repository.mapper;

import com.telegram.rtb.model.domain.UserReputation;
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
        userReputation.setChatId(rs.getLong("chat_id"));
        userReputation.setReputationValue(rs.getInt("reputation_value"));
        userReputation.setUpdatedDatetime(rs.getTimestamp("updated_date_time"));
        userReputation.setUpdatedFrom(rs.getInt("updated_from_id"));
        return userReputation;
    }

}