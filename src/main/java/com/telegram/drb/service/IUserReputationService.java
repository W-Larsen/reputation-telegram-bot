package com.telegram.drb.service;

import com.telegram.drb.model.domain.UserReputation;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

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
     * @param user   the user
     * @param chat   the chat
     * @param action the action
     * @return response
     */
    String manageUserReputation(User user, Chat chat, Consumer<UserReputation> action);

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
