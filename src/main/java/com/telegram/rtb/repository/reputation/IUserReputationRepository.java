package com.telegram.rtb.repository.reputation;

import com.telegram.rtb.model.domain.Sort;
import com.telegram.rtb.model.domain.UserReputation;

import java.util.List;

/**
 * User reputation repository interface.
 *
 * @author Valentyn Korniienko
 */
public interface IUserReputationRepository {

    /**
     * Create user reputation.
     *
     * @param userReputation the user reputation
     * @return user reputation
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

    /**
     * Finds all user reputations.
     *
     * @param sort the sort
     * @return list of user reputations
     */
    List<UserReputation> findAll(Sort sort);

    /**
     * Update reputation value.
     *
     * @param userId          the user id
     * @param chatId          the chat id
     * @param reputationValue the reputation value
     */
    void updateUserReputation(Integer userId, Long chatId, Integer reputationValue);
}
