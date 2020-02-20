package com.telegram.drb.command;

import com.telegram.drb.service.reputation.IUserReputationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Command to show top user reputations.
 *
 * @author Valentyn Korniienko
 */
@Component("/toprep")
public class ShowTopReputationCommand implements Command {

    @Autowired
    private IUserReputationService userReputationService;

    @Override
    public String execute(Message message) {

        return null;
    }
}
