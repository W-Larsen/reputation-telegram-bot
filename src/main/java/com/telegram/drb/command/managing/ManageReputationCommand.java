package com.telegram.drb.command.managing;

import com.telegram.drb.command.AbstractCommand;
import com.telegram.drb.command.Command;
import com.telegram.drb.model.domain.UserReputation;
import com.telegram.drb.model.message.BotApiMethodResponse;
import com.telegram.drb.service.reputation.IUserReputationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

import static com.telegram.drb.model.message.MethodName.MANAGE_REPUTATION;

/**
 * Abstract managing reputation class.
 *
 * @author Valentyn Korniienko
 */
public abstract class ManageReputationCommand extends AbstractCommand implements Command {

    @Autowired
    private Queue<Message> messageQueue;

    @Autowired
    private IUserReputationService userReputationService;

    protected BotApiMethodResponse executeChangingReputation(Message message, Consumer<UserReputation> action, String actionMessage) {
        if (message.isReply()) {
            User repliedTo = message.getReplyToMessage().getFrom();
            if (!repliedTo.getBot()) {
                List<BotApiMethod<?>> botApiMethodsResponse = new LinkedList<>();

                if (!CollectionUtils.isEmpty(messageQueue)) {
                    Message previousMessage = messageQueue.remove();
                    botApiMethodsResponse.add(createDefaultDeleteMessageResponse(message.getChatId(), previousMessage.getMessageId()));
                }

                String responseText = userReputationService.manageUserReputation(message.getFrom(), repliedTo, message.getChat(), action, actionMessage);
                botApiMethodsResponse.add(createDefaultSendMessageResponse(message.getChatId(), responseText));
                return createBotApiMethodResponse(botApiMethodsResponse, MANAGE_REPUTATION);
            }
        }
        return null;
    }

}
