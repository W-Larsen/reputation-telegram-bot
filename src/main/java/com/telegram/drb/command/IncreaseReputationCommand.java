package com.telegram.drb.command;

import static com.telegram.drb.constants.Messages.INCREASED_RU;

import com.telegram.drb.service.reputation.IUserReputationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

/**
 * Increase reputation command.
 *
 * @author Valentyn Korniienko
 */
@Component("+")
public class IncreaseReputationCommand implements Command {

    @Autowired
    private IUserReputationService userReputationService;

    @Override
    public SendMessage execute(Message message) {
        if (message.isReply()) {
            User repliedTo = message.getReplyToMessage().getFrom();
            String responseText = userReputationService.manageUserReputation(message.getFrom(), repliedTo, message.getChat(),
                userReputation -> userReputationService.increaseUserReputation(userReputation), INCREASED_RU);
            return createResponseSendMessage(message.getChatId(), responseText);
        }
        return null;
    }

    private SendMessage createResponseSendMessage(Long chatId, String responseText) {
        SendMessage response = new SendMessage();
        response.setChatId(chatId);
        response.setText(responseText);
        return response;
    }

}
