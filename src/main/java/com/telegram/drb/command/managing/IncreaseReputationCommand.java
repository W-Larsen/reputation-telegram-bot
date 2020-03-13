package com.telegram.drb.command.managing;

import com.telegram.drb.model.message.BotApiMethodResponse;
import com.telegram.drb.service.reputation.IUserReputationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

import static com.telegram.drb.constants.Messages.INCREASED_RU;

/**
 * Increase reputation command.
 *
 * @author Valentyn Korniienko
 */
@Component("+")
public class IncreaseReputationCommand extends ManageReputationCommand {

    @Autowired
    private IUserReputationService userReputationService;

    @Override
    public BotApiMethodResponse execute(Message message) {
        return executeChangingReputation(message, userReputation ->
                userReputationService.increaseUserReputation(userReputation, message.getFrom()), INCREASED_RU);
    }

}
