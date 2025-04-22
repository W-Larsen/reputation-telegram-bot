package com.telegram.rtb.controller;

import com.telegram.rtb.exception.BadRequestException;
import com.telegram.rtb.model.rest.reputation.ReputationRequest;
import com.telegram.rtb.model.rest.reputation.ReputationResponse;
import com.telegram.rtb.service.reputation.IUserReputationService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ReputationController {

    @Autowired
    private IUserReputationService userReputationService;

    @PostMapping(value = "/reputation/update")
    public ReputationResponse updateReputation(@RequestBody ReputationRequest request) {
        checkIfUserReputationIsExist(request.getUserId(), request.getChatId());
        return userReputationService.updateUserReputation(request);
    }

    private void checkIfUserReputationIsExist(Long userId, Long chatId) {
        userReputationService.findByUserIdAndChatId(userId, chatId)
                .orElseThrow(() -> {
                    String message = String.format("User with such id %s and chat id %s does not exist", userId, chatId);
                    log.warn(message);
                    throw new BadRequestException(message);
                });
    }
}
