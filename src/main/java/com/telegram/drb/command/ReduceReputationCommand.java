package com.telegram.drb.command;

import com.telegram.drb.service.IUserReputationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
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
    public String execute(User user, Chat chat) {
        return userReputationService.manageUserReputation(user, chat,
                userReputation -> userReputationService.reduceUserReputation(userReputation));
    }
}
