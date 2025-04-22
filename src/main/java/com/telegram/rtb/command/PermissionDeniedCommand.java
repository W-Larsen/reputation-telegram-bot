package com.telegram.rtb.command;

import com.telegram.rtb.model.message.BotApiMethodResponse;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.message.Message;

import java.util.List;

import static com.telegram.rtb.constants.Messages.PERMISSION_DENIED_FOR_COMMAND;
import static com.telegram.rtb.model.message.MethodName.PERMISSION_DENIED;
import static com.telegram.rtb.util.BotApiMethodCreator.createDefaultSendMessage;

/**
 * Permission denied command.
 *
 * @author Valentyn Korniienko
 */
@Component
public class PermissionDeniedCommand extends AbstractCommand implements Command {

    @Override
    public BotApiMethodResponse execute(Message message) {
        return createBotApiMethodResponse(List.of(createDefaultSendMessage(message, PERMISSION_DENIED_FOR_COMMAND)), PERMISSION_DENIED);
    }
}
