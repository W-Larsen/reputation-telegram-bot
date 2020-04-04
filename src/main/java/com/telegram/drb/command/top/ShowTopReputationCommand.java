package com.telegram.drb.command.top;

import com.telegram.drb.model.domain.Sort;
import com.telegram.drb.model.message.BotApiMethodResponse;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Command to show top user reputations.
 *
 * @author Valentyn Korniienko
 */
@Component("/toprep@dawgReputationBot")
public class ShowTopReputationCommand extends ShowReputationCommand {

    @Override
    public BotApiMethodResponse execute(Message message) {
        return executeShowReputation(message, Sort.DESC);
    }

}
