package com.telegram.drb.command;

import com.telegram.drb.service.reputation.IUserReputationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

import static com.telegram.drb.constants.Messages.INCREASED_RU;

/**
 * Increase reputation command.
 *
 * @author Valentyn Korniienko
 */
@Component("+")
public class IncreaseReputationCommand extends AbstractCommand implements Command {

    @Autowired
    private IUserReputationService userReputationService;

    @Override
    public SendMessage execute(Message message) {
        if (message.isReply()) {
            User repliedTo = message.getReplyToMessage().getFrom();
            if (!repliedTo.getUserName().equals(botUserName)) {
                String responseText = userReputationService.manageUserReputation(message.getFrom(), repliedTo, message.getChat(),
                        userReputation -> userReputationService.increaseUserReputation(userReputation, message.getFrom()), INCREASED_RU);
                return createDefaultMessageResponse(message.getChatId(), responseText);
            }
        }
        return null;
    }

}
