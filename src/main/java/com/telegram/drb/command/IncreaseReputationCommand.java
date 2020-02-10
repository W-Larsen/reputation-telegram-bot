package com.telegram.drb.command;

import com.telegram.drb.model.domain.UserReputation;
import com.telegram.drb.service.UserReputationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

import javax.annotation.PostConstruct;

import static com.telegram.drb.util.UserReputationUtil.createUserReputation;

/**
 * Increase reputation command.
 *
 * @author Valentyn Korniienko
 */
@Component("+")
public class IncreaseReputationCommand implements Command {

    @Autowired
    private UserReputationService userReputationService;

    @Override
    public String execute(User user, Chat chat) {
        UserReputation userReputation = userReputationService.findByUserIdAndChatId(user.getId(), chat.getId());
        if (userReputation == null) {
            userReputationService.createUserReputation(createUserReputation(user.getId(), chat.getId()));
        }
        userReputationService.increaseUserReputation(userReputation);

        UserReputation actualUserReputation = userReputationService.findByUserIdAndChatId(user.getId(), chat.getId());
        return String.format("User %s reputation from chat %s is %s",
                user.getUserName(), chat.getFirstName(), actualUserReputation.getReputationValue());
    }

}
