package com.telegram.drb.rest;

import com.telegram.drb.exception.BadRequestException;
import com.telegram.drb.model.rest.reputation.ReputationRequest;
import com.telegram.drb.model.rest.reputation.ReputationResponse;
import com.telegram.drb.service.reputation.IUserReputationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Reputation rest.
 *
 * @author Valentyn Korniienko
 */
@RestController
public class ReputationRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReputationRest.class);

    @Autowired
    private IUserReputationService userReputationService;

    @PostMapping(value = "/reputation/update")
    public ReputationResponse updateReputation(@RequestBody ReputationRequest request) {
        checkIfUserReputationIsExist(request.getUserId(), request.getChatId());
        return userReputationService.updateUserReputation(request);
    }

    private void checkIfUserReputationIsExist(Integer userId, Long chatId) {
        if (userReputationService.findByUserIdAndChatId(userId, chatId) == null) {
            String message = String.format("User with such id %s and chat id %s does not exist", userId, chatId);
            LOGGER.warn(message);
            throw new BadRequestException(message);
        }
    }
}
