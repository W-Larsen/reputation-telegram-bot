package com.telegram.drb.command.show.top;

import com.telegram.drb.model.domain.Sort;
import com.telegram.drb.model.message.BotApiMethodResponse;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Command to show users with min reputation.
 *
 * @author Valentyn Korniienko
 */
@Component("/minrep@dawgReputationBot")
public class ShowMinReputationCommand extends ShowReputationCommand {

    @Override
    public BotApiMethodResponse execute(Message message) {
        return executeShowReputation(message, Sort.ASC);
    }

}
