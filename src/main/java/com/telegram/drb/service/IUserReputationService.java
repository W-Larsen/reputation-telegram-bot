package com.telegram.drb.service;

import com.telegram.drb.model.domain.UserReputation;

import java.util.Optional;

/**
 * User reputation service interface.
 *
 * @author Valentyn Korniienko
 */
public interface IUserReputationService {

    /**
     * Create user reputation.
     *
     * @param userReputation the user reputation
     * @return the user reputation
     */
    UserReputation createUserReputation(UserReputation userReputation);

    /**
     * Find user reputation by user id and chat id.
     *
     * @param userId the user id
     * @param chatId the chat id
     * @return user reputation
     */
    UserReputation findByUserIdAndChatId(Integer userId, Long chatId);

    /**
     * Increase user reputation.
     *
     * @param userReputation the user reputation
     */
    void increaseUserReputation(UserReputation userReputation);

    /**
     * Reduce user reputation.
     *
     * @param userReputation the user reputation
     */
    void reduceUserReputation(UserReputation userReputation);

}
