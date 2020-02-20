package com.telegram.drb.command;

import com.telegram.drb.service.reputation.IUserReputationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

/**
 * Reduce reputation command.
 *
 * @author Valentyn Korniienko
 */
@Component("-")
public class ReduceReputationCommand implements Command {

    @Autowired
    private IUserReputationService userReputationService;

    @Override
    public String execute(Message message) {
        User repliedTo = message.getReplyToMessage().getFrom();
        return userReputationService.manageUserReputation(message.getFrom(), repliedTo, message.getChat(),
                userReputation -> userReputationService.reduceUserReputation(userReputation), "уменьшил");
    }
}
