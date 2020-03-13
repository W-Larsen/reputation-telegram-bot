package com.telegram.drb.command.managing;

import com.telegram.drb.model.message.BotApiMethodResponse;
import com.telegram.drb.service.reputation.IUserReputationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

import static com.telegram.drb.constants.Messages.REDUCED_RU;

/**
 * Reduce reputation command.
 *
 * @author Valentyn Korniienko
 */
@Component("-")
public class ReduceReputationCommand extends ManageReputationCommand {

    @Autowired
    private IUserReputationService userReputationService;

    @Override
    public BotApiMethodResponse execute(Message message) {
        return executeChangingReputation(message, userReputation ->
                userReputationService.reduceUserReputation(userReputation, message.getFrom()), REDUCED_RU);
    }

}
