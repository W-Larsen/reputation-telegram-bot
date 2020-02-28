package com.telegram.drb.command;

import com.telegram.drb.model.domain.TelegramUser;
import com.telegram.drb.model.domain.UserReputation;
import com.telegram.drb.service.reputation.IUserReputationService;
import com.telegram.drb.service.user.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

/**
 * Command to show top user reputations.
 *
 * @author Valentyn Korniienko
 */
@Component("/toprep@dawgReputationBot")
public class ShowTopReputationCommand implements Command {

    @Autowired
    private IUserReputationService userReputationService;
    @Autowired
    private IUserService userService;

    private static final long LIMIT = 10;

    @Override
    public String execute(Message message) {
        StringBuilder response = new StringBuilder();
        List<UserReputation> orderedByReputation = userReputationService.findAll(LIMIT);
        orderedByReputation.forEach(userReputation -> {
            TelegramUser user = userService.findById(userReputation.getUserId());
            if (user != null) {
                response.append(userReputation.getReputationValue()).append("  ").append(getFullName(user));
            }
            response.append(System.lineSeparator());
        });
        return response.toString();
    }


    private String getFullName(TelegramUser user) {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        if (StringUtils.isNotEmpty(lastName)) {
            return firstName + " " + lastName;
        }
        return firstName;
    }
}
