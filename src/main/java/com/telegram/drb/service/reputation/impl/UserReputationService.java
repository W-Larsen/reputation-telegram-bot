package com.telegram.drb.service.reputation.impl;

import com.telegram.drb.model.domain.TelegramChat;
import com.telegram.drb.model.domain.TelegramUser;
import com.telegram.drb.model.domain.UserReputation;
import com.telegram.drb.repository.reputation.IUserReputationRepository;
import com.telegram.drb.service.chat.IChatService;
import com.telegram.drb.service.reputation.IUserReputationService;
import com.telegram.drb.service.user.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.function.Consumer;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

/**
 * User reputation service implementation.
 *
 * @author Valentyn Korniienko
 */
@Service
public class UserReputationService implements IUserReputationService {

    @Autowired
    private IUserReputationRepository userReputationRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private IChatService chatService;

    @Override
    public UserReputation createUserReputation(UserReputation userReputation) {
        return userReputationRepository.createUserReputation(userReputation);
    }

    @Override
    public UserReputation findByUserIdAndChatId(Integer userId, Long chatId) {
        return userReputationRepository.findByUserIdAndChatId(userId, chatId);
    }

    @Override
    @Transactional
    public String manageUserReputation(User fromBy, User repliedTo, Chat chat, Consumer<UserReputation> action, String actionMessage) {
        UserReputation reputationFromBy = userReputationRepository.findByUserIdAndChatId(fromBy.getId(), chat.getId());
        UserReputation reputationRepliedTo = userReputationRepository.findByUserIdAndChatId(repliedTo.getId(), chat.getId());

        if (reputationFromBy == null) {
            reputationFromBy = createUserRelation(fromBy, chat);
        }
        if (reputationRepliedTo == null) {
            reputationRepliedTo = createUserRelation(repliedTo, chat);
        }

        if (reputationFromBy.getUserId().equals(reputationRepliedTo.getUserId())) {
            return StringUtils.EMPTY;
        }

        action.accept(reputationRepliedTo);

        UserReputation actualRepliedToReputation = userReputationRepository.findByUserIdAndChatId(repliedTo.getId(), chat.getId());
        return String.format("%s (%s) %s репутацию %s (%s)",
                getFullName(fromBy), reputationFromBy.getReputationValue(), actionMessage,
                getFullName(repliedTo), actualRepliedToReputation.getReputationValue());
    }

    private UserReputation createUserRelation(User user, Chat chat) {
        userService.addTelegramUser(toTelegramUser(user));
        chatService.addTelegramChat(toTelegramChat(chat));
        return userReputationRepository.createUserReputation(createUserReputationClass(user.getId(), chat.getId()));
    }

    private TelegramUser toTelegramUser(User user) {
        TelegramUser telegramUser = new TelegramUser();
        telegramUser.setUserId(user.getId());
        telegramUser.setUserName(user.getUserName());
        telegramUser.setFirstName(user.getFirstName());
        telegramUser.setLastName(user.getLastName());
        return telegramUser;
    }

    private TelegramChat toTelegramChat(Chat chat) {
        TelegramChat telegramChat = new TelegramChat();
        telegramChat.setChatId(chat.getId());
        telegramChat.setChatName(chat.getTitle());
        return telegramChat;
    }

    private String getFullName(User user) {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        if (StringUtils.isNotEmpty(lastName)) {
            return firstName + " " + lastName;
        }
        return firstName;
    }

    @Override
    public void increaseUserReputation(UserReputation userReputation) {
        userReputationRepository.increaseUserReputation(userReputation);
    }

    @Override
    public void reduceUserReputation(UserReputation userReputation) {
        userReputationRepository.reduceUserReputation(userReputation);
    }

    private UserReputation createUserReputationClass(Integer userId, Long chatId) {
        UserReputation userReputation = new UserReputation();
        userReputation.setUserId(userId);
        userReputation.setChatId(chatId);
        userReputation.setReputationValue(INTEGER_ZERO);
        return userReputation;
    }

}
