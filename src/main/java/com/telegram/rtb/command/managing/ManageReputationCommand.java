package com.telegram.rtb.command.managing;

import com.telegram.rtb.command.AbstractCommand;
import com.telegram.rtb.command.Command;
import com.telegram.rtb.model.cache.MessageCache;
import com.telegram.rtb.model.domain.UserReputation;
import com.telegram.rtb.model.message.BotApiMethodResponse;
import com.telegram.rtb.service.reputation.IUserReputationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.message.Message;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

import static com.telegram.rtb.model.message.MethodName.MANAGE_REPUTATION;

/**
 * Abstract managing reputation class.
 *
 * @author Valentyn Korniienko
 */
@Slf4j
public abstract class ManageReputationCommand extends AbstractCommand implements Command {

    @Autowired
    private MessageCache messageCache;

    @Autowired
    private IUserReputationService userReputationService;

    protected BotApiMethodResponse executeChangingReputation(Message message, Consumer<UserReputation> action, String actionMessage) {
        if (message.isReply()) {
            User repliedTo = message.getReplyToMessage().getFrom();
            if (!repliedTo.getIsBot() && !isTheSameUser(message.getFrom(), repliedTo)) {
                List<BotApiMethod<?>> botApiMethodsResponse = new LinkedList<>();

                if (!messageCache.isEmpty()) {
                    Queue<Message> messages = messageCache.get(message.getChatId());
                    if (!CollectionUtils.isEmpty(messages)) {
                        log.info("Queue of messages are not empty, size: {}", messages.size());
                        Message previousMessage = messages.remove();
                        log.info("Deleting message: {}", previousMessage.getMessageId());
                        botApiMethodsResponse.add(createDefaultDeleteMessageResponse(message.getChatId(), previousMessage.getMessageId()));
                    }
                }

                String responseText = userReputationService.manageUserReputation(message.getFrom(), repliedTo, message.getChat(), action, actionMessage);
                botApiMethodsResponse.add(createDefaultSendMessageResponse(message.getChatId(), responseText));
                return createBotApiMethodResponse(botApiMethodsResponse, MANAGE_REPUTATION);
            }
        }
        return null;
    }

    private boolean isTheSameUser(User messageFrom, User repliedTo) {
        return messageFrom.getId().equals(repliedTo.getId());
    }

}
