package com.telegram.rtb.command.managing;

import com.telegram.rtb.model.message.BotApiMethodResponse;
import com.telegram.rtb.service.reputation.IUserReputationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.message.Message;

import static com.telegram.rtb.constants.Messages.REDUCED_RU;

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
