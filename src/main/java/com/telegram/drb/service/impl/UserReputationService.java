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
    public String manageUserReputation(User fromBy, User repliedTo, Chat chat, Consumer<UserReputation> action, String actionMessage) {
        UserReputation reputationFromBy = userReputationRepository.findByUserIdAndChatId(fromBy.getId(), chat.getId());
        UserReputation reputationRepliedTo = userReputationRepository.findByUserIdAndChatId(repliedTo.getId(), chat.getId());

        if (reputationFromBy == null) {
            reputationFromBy = userReputationRepository.createUserReputation(createUserReputationClass(repliedTo.getId(), chat.getId()));
        }
        if (reputationRepliedTo == null) {
            reputationRepliedTo = userReputationRepository.createUserReputation(createUserReputationClass(repliedTo.getId(), chat.getId()));
        }

        action.accept(reputationRepliedTo);

        UserReputation actualRepliedToReputation = userReputationRepository.findByUserIdAndChatId(repliedTo.getId(), chat.getId());
        return String.format("%s (%s) %s репутацию %s (%s)",
                fromBy.getUserName(), reputationFromBy.getReputationValue(), actionMessage,
                repliedTo.getFirstName(), actualRepliedToReputation.getReputationValue());
    }

    @Override
    public void increaseUserReputation(UserReputation userReputation) {
        userReputationRepository.increaseUserReputation(userReputation);
    }

    @Override
    public void reduceUserReputation(UserReputation userReputation) {
        userReputationRepository.reduceUserReputation(userReputation);
    }

    private UserReputation createUserReputationClass(Integer userId, Long chatId) {
        UserReputation userReputation = new UserReputation();
        userReputation.setUserId(userId);
        userReputation.setChatId(Math.toIntExact(chatId));
        userReputation.setReputationValue(INTEGER_ZERO);
        return userReputation;
    }

}
