package com.telegram.drb.service.user.impl;

import com.telegram.drb.model.domain.TelegramUser;
import com.telegram.drb.repository.user.IUserRepository;
import com.telegram.drb.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User service implementation.
 *
 * @author Valentyn Korniienko
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public void addTelegramUser(TelegramUser telegramUser) {
        if (userRepository.findUserById(telegramUser.getUserId()) == null) {
            userRepository.addTelegramUser(telegramUser);
        }
    }
}
