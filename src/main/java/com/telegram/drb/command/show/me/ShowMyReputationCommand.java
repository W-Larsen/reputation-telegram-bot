package com.telegram.drb.command.show.me;

import com.telegram.drb.command.AbstractCommand;
import com.telegram.drb.command.Command;
import com.telegram.drb.model.domain.UserReputation;
import com.telegram.drb.model.message.BotApiMethodResponse;
import com.telegram.drb.service.reputation.IUserReputationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Collections;

import static com.telegram.drb.model.domain.ParseMode.MARKDOWN_V2;
import static com.telegram.drb.model.message.MethodName.SHOW_MY_REPUTATION;
import static com.telegram.drb.util.UserUtils.getFullName;

/**
 * Command to show reputation of certain user.
 *
 * @author Valentyn Korniienko
 */
@Component("/myrep@dawgReputationBot")
public class ShowMyReputationCommand extends AbstractCommand implements Command {

    @Autowired
    private IUserReputationService userReputationService;

    @Override
    public BotApiMethodResponse execute(Message message) {
        User from = message.getFrom();
        UserReputation userReputation = userReputationService.findByUserIdAndChatId(from.getId(), message.getChatId());
        int actualReputation = userReputation == null ? 0 : userReputation.getReputationValue();
        String responseText = String.format("*%s*, твоя репутация %s", getFullName(from), "*" + prepareReputationValue(actualReputation) + "*");

        return createBotApiMethodResponse(Collections.singletonList(createResponseSendMessage(message, responseText)), SHOW_MY_REPUTATION);
    }

    private String prepareReputationValue(int actualValue) {
        if (actualValue < 0) {
            return "\\" + actualValue;
        }
        return String.valueOf(actualValue);
    }

    private SendMessage createResponseSendMessage(Message message, String responseText) {
        SendMessage messageResponse = createDefaultSendMessageResponse(message.getChatId(), responseText);
        messageResponse.setParseMode(MARKDOWN_V2.getValue());
        messageResponse.setReplyToMessageId(message.getMessageId());
        return messageResponse;
    }

}
