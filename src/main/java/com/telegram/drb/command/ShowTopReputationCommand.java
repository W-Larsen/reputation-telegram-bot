package com.telegram.drb.command;

import com.telegram.drb.model.domain.TelegramUser;
import com.telegram.drb.model.domain.UserReputation;
import com.telegram.drb.model.message.BotApiMethodResponse;
import com.telegram.drb.service.reputation.IUserReputationService;
import com.telegram.drb.service.user.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;

import java.util.Collections;
import java.util.List;

import static com.telegram.drb.model.domain.ParseMode.MARKDOWN;
import static com.telegram.drb.model.message.MethodName.SHOT_TOP_REPUTATION;

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
    public BotApiMethodResponse execute(Message message) {
        StringBuilder responseText = new StringBuilder();
        List<UserReputation> orderedByReputation = userReputationService.findAll(defaultLimit);
        orderedByReputation.forEach(userReputation -> {
            TelegramUser user = userService.findById(userReputation.getUserId());
            if (user != null) {
                responseText
                        .append("`").append(userReputation.getReputationValue()).append("`")
                        .append("  ")
                        .append("*").append(getFullName(user)).append("*");
            }
            responseText.append(System.lineSeparator());
        });
        return createBotApiMethodResponse(Collections.singletonList(createResponseSendMessage(message, responseText.toString())), SHOT_TOP_REPUTATION);
    }

    private String getFullName(TelegramUser user) {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        if (StringUtils.isNotEmpty(lastName)) {
            return firstName + " " + lastName;
        }
        return firstName;
    }

    private SendMessage createResponseSendMessage(Message message, String responseText) {
        SendMessage defaultMessageResponse = createDefaultSendMessageResponse(message.getChatId(), responseText);
        defaultMessageResponse.setParseMode(MARKDOWN.getValue());
        defaultMessageResponse.disableWebPagePreview();
        return defaultMessageResponse;
    }

}
