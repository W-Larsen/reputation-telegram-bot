package com.telegram.drb.service.user;

import com.telegram.drb.model.domain.TelegramUser;

/**
 * User service interface.
 *
 * @author Valentyn Korniienko
 */
public interface IUserService {

    /**
     * Add user.
     *
     * @param telegramUser the telegram user
     */
    void addTelegramUser(TelegramUser telegramUser);

    /**
     * Finds by user id.
     *
     * @param userId the user id
     * @return telegram user
     */
    TelegramUser findById(Integer userId);
}
