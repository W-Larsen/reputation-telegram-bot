package com.telegram.rtb.service.reputation;

import com.telegram.rtb.model.domain.UserReputation;
import com.telegram.rtb.model.rest.reputation.ReputationRequest;
import com.telegram.rtb.model.rest.reputation.ReputationResponse;
import org.springframework.data.domain.Sort;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.List;
import java.util.function.Consumer;

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
     * Manage user reputation.
     *
     * @param fromBy        the from user
     * @param repliedTo     the replied to user
     * @param chat          the chat
     * @param action        the action
     * @param actionMessage the action message
     * @return response
     */
    String manageUserReputation(User fromBy, User repliedTo, Chat chat, Consumer<UserReputation> action, String actionMessage);

    /**
     * Increase user reputation.
     *
     * @param userReputation the user reputation
     * @param updatedFrom    the updated from user
     */
    void increaseUserReputation(UserReputation userReputation, User updatedFrom);

    /**
     * Reduce user reputation.
     *
     * @param userReputation the user reputation
     * @param updatedFrom    the updated from user
     */
    void reduceUserReputation(UserReputation userReputation, User updatedFrom);

    /**
     * Finds all user reputations.
     *
     * @param limit the limit
     * @param sort  the sort
     * @return list of user reputations
     */
    List<UserReputation> findAll(long limit, Sort sort);

    /**
     * Update user reputation.
     *
     * @param reputationRequest the reputation request
     * @return reputation response
     */
    ReputationResponse updateUserReputation(ReputationRequest reputationRequest);
}
