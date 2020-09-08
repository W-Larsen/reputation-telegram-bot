package com.telegram.rtb.service.user.impl;

import com.telegram.rtb.model.domain.TelegramUser;
import com.telegram.rtb.repository.user.IUserRepository;
import com.telegram.rtb.service.user.IUserService;
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

    @Override
    public TelegramUser findById(Integer userId) {
        return userRepository.findUserById(userId);
    }
}
