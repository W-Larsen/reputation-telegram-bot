package com.telegram.rtb.command.top;

import com.telegram.rtb.command.AbstractCommand;
import com.telegram.rtb.model.domain.TelegramUser;
import com.telegram.rtb.model.domain.UserReputation;
import com.telegram.rtb.model.message.BotApiMethodResponse;
import com.telegram.rtb.service.reputation.IUserReputationService;
import com.telegram.rtb.service.user.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

import static com.telegram.rtb.model.domain.ParseMode.MARKDOWN;
import static com.telegram.rtb.model.message.MethodName.SHOW_LIST_REPUTATION;
import static java.util.Collections.singletonList;

public abstract class ShowReputationCommand extends AbstractCommand {

    @Autowired
    private IUserReputationService userReputationService;
    @Autowired
    private IUserService userService;

    @Value("${telegram.default.top.limit}")
    private int defaultLimit;

    protected BotApiMethodResponse executeShowReputation(Message message, Sort sort) {
        StringBuilder responseText = new StringBuilder();
        List<UserReputation> orderedByReputation = userReputationService.findAll(defaultLimit, sort);
        orderedByReputation.forEach(userReputation -> {
            TelegramUser user = userService.findById(userReputation.getUserReputationPk().getUserId());
            if (user != null) {
                responseText
                        .append("`").append(userReputation.getReputationValue()).append("`")
                        .append("  ")
                        .append("*").append(getFullName(user)).append("*");
            }
            responseText.append(System.lineSeparator());
        });
        return createBotApiMethodResponse(singletonList(createResponseSendMessage(message, responseText.toString())), SHOW_LIST_REPUTATION);
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
