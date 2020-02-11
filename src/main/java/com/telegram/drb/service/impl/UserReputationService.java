package com.telegram.drb.service.impl;

import com.telegram.drb.model.domain.UserReputation;
import com.telegram.drb.repository.IUserReputationRepository;
import com.telegram.drb.service.IUserReputationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.function.Consumer;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

/**
 * User reputation service implementation.
 *
 * @author Valentyn Korniienko
 */
@Service
public class UserReputationService implements IUserReputationService {

    @Autowired
    private IUserReputationRepository userReputationRepository;

    @Override
    public UserReputation createUserReputation(UserReputation userReputation) {
        return userReputationRepository.createUserReputation(userReputation);
    }

    @Override
    public UserReputation findByUserIdAndChatId(Integer userId, Long chatId) {
        return userReputationRepository.findByUserIdAndChatId(userId, chatId);
    }

    @Override
    public String manageUserReputation(User user, Chat chat, Consumer<UserReputation> action) {
        UserReputation userReputation = userReputationRepository.findByUserIdAndChatId(user.getId(), chat.getId());
        if (userReputation == null) {
            userReputation = userReputationRepository.createUserReputation(createUserReputationClass(user.getId(), chat.getId()));
        }

        action.accept(userReputation);

        UserReputation actualUserReputation = userReputationRepository.findByUserIdAndChatId(user.getId(), chat.getId());
        return String.format("User %s reputation from chat %s is %s",
                user.getUserName(), chat.getFirstName(), actualUserReputation.getReputationValue());
    }

    @Override
    public void increaseUserReputation(UserReputation userReputation) {
        userReputationRepository.increaseUserReputation(userReputation);
    }

    @Override
    public void reduceUserReputation(UserReputation userReputation) {
        userReputationRepository.reduceUserReputation(userReputation);
    }

    public UserReputation createUserReputationClass(Integer userId, Long chatId) {
        UserReputation userReputation = new UserReputation();
        userReputation.setUserId(userId);
        userReputation.setChatId(Math.toIntExact(chatId));
        userReputation.setReputationValue(INTEGER_ZERO);
        return userReputation;
    }

}
