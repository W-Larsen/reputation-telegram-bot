package com.telegram.drb.command;

import com.telegram.drb.model.domain.TelegramUser;
import com.telegram.drb.model.domain.UserReputation;
import com.telegram.drb.service.reputation.IUserReputationService;
import com.telegram.drb.service.user.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

/**
 * Command to show top user reputations.
 *
 * @author Valentyn Korniienko
 */
@Component("/toprep@dawgReputationBot")
public class ShowTopReputationCommand extends AbstractCommand implements Command {

    @Autowired
    private IUserReputationService userReputationService;
    @Autowired
    private IUserService userService;

    @Value("${dawg.default.top.limit}")
    private int defaultLimit;

    @Override
    public SendMessage execute(Message message) {
        StringBuilder responseText = new StringBuilder();
        List<UserReputation> orderedByReputation = userReputationService.findAll(defaultLimit);
        orderedByReputation.forEach(userReputation -> {
            TelegramUser user = userService.findById(userReputation.getUserId());
            if (user != null) {
                responseText
                        .append("`").append(userReputation.getReputationValue()).append("`")
                        .append("  ")
                        .append("[").append(getFullName(user)).append("]").append("(tg://user?id=").append(user.getUserId()).append(")");
            }
            responseText.append(System.lineSeparator());
        });
        return createResponseSendMessage(message.getChatId(), responseText.toString());
    }

    private String getFullName(TelegramUser user) {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        if (StringUtils.isNotEmpty(lastName)) {
            return firstName + " " + lastName;
        }
        return firstName;
    }

    private SendMessage createResponseSendMessage(Long chatId, String responseText) {
        SendMessage defaultMessageResponse = createDefaultMessageResponse(chatId, responseText);
        defaultMessageResponse.setParseMode("MarkdownV2");
        return defaultMessageResponse;
    }

}
