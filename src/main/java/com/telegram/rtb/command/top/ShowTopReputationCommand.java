package com.telegram.rtb.command.top;

import com.telegram.rtb.model.message.BotApiMethodResponse;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.message.Message;

/**
 * Command to show top user reputations.
 *
 * @author Valentyn Korniienko
 */
@Component("/toprep")
public class ShowTopReputationCommand extends ShowReputationCommand {

    @Override
    public BotApiMethodResponse execute(Message message) {
        return executeShowReputation(message, Sort.by("reputationValue").descending());
    }

}
