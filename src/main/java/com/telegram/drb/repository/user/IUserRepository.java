package com.telegram.drb.repository.user;

import com.telegram.drb.model.domain.TelegramUser;

/**
 * User repository interface.
 *
 * @author Valentyn Korniienko
 */
public interface IUserRepository {

    /**
     * Add telegram user.
     *
     * @param telegramUser the telegram user
     * @return created telegram user
     */
    TelegramUser addTelegramUser(TelegramUser telegramUser);

    /**
     * Find user by user id.
     *
     * @param userId the user id
     * @return user
     */
    TelegramUser findUserById(Integer userId);
}