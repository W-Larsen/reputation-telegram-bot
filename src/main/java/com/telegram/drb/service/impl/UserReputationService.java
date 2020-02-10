package com.telegram.drb.service.impl;

import com.telegram.drb.model.domain.UserReputation;
import com.telegram.drb.repository.IUserReputationRepository;
import com.telegram.drb.service.IUserReputationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public void increaseUserReputation(UserReputation userReputation) {
        userReputationRepository.increaseUserReputation(userReputation);
    }

    @Override
    public void reduceUserReputation(UserReputation userReputation) {
        userReputationRepository.reduceUserReputation(userReputation);
    }

}
