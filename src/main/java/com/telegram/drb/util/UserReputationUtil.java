package com.telegram.drb.util;

import com.telegram.drb.model.domain.UserReputation;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

/**
 * User reputation util.
 *
 * @author Valentyn Korniienko
 */
public final class UserReputationUtil {

    /**
     * Create user reputation class.
     *
     * @param userId the user id
     * @param chatId the chat id
     * @return user reputation
     */
    public static UserReputation createUserReputationClass(Integer userId, Long chatId) {
        UserReputation userReputation = new UserReputation();
        userReputation.setUserId(userId);
        userReputation.setChatId(Math.toIntExact(chatId));
        userReputation.setReputationValue(INTEGER_ZERO);
        return userReputation;
    }

}
