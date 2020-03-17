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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.telegram.drb.constants.Messages.DELAY_MESSAGE_RU;
import static com.telegram.drb.util.UserUtils.getFullName;
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

    @Value("${dawg.default.delay}")
    private int defaultDelay;

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

        if (!isValidUpdatedTime(fromBy.getId(), reputationRepliedTo)) {
            return DELAY_MESSAGE_RU;
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
        return userReputationRepository.createUserReputation(createUserReputationClass(user.getId(), chat.getId(), user.getId()));
    }

    private boolean isValidUpdatedTime(Integer fromById, UserReputation repliedTo) {
        if (repliedTo.getUpdatedFrom().equals(fromById)) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime updatedDateTime = repliedTo.getUpdatedDatetime().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            Duration duration = Duration.between(now, updatedDateTime);
            // convert to seconds
            long diff = Math.abs(duration.toMillis()) / 1000;
            return diff > defaultDelay;
        }
        return true;
    }

    private TelegramUser toTelegramUser(User user) {
        TelegramUser telegramUser = new TelegramUser();
        telegramUser.setUserId(user.getId());
        telegramUser.setUserName(getUserName(user));
        telegramUser.setFirstName(user.getFirstName());
        telegramUser.setLastName(user.getLastName());
        return telegramUser;
    }

    private String getUserName(User user) {
        return StringUtils.isEmpty(user.getUserName()) ? user.getFirstName() : user.getFirstName();
    }

    private TelegramChat toTelegramChat(Chat chat) {
        TelegramChat telegramChat = new TelegramChat();
        telegramChat.setChatId(chat.getId());
        telegramChat.setChatName(chat.getTitle());
        return telegramChat;
    }

    @Override
    public void increaseUserReputation(UserReputation userReputation, User updatedFrom) {
        userReputation.setUpdatedDatetime(Calendar.getInstance().getTime());
        userReputation.setUpdatedFrom(updatedFrom.getId());
        userReputationRepository.increaseUserReputation(userReputation);
    }

    @Override
    public void reduceUserReputation(UserReputation userReputation, User updatedFrom) {
        userReputation.setUpdatedDatetime(Calendar.getInstance().getTime());
        userReputation.setUpdatedFrom(updatedFrom.getId());
        userReputationRepository.reduceUserReputation(userReputation);
    }

    @Override
    public List<UserReputation> findAll(long limit) {
        return userReputationRepository.findAll().stream()
                .limit(limit)
                .sorted(Comparator.comparing(UserReputation::getReputationValue).reversed()
                        .thenComparing(UserReputation::getUserId))
                .collect(Collectors.toList());
    }

    private UserReputation createUserReputationClass(Integer userId, Long chatId, Integer updatedFromId) {
        UserReputation userReputation = new UserReputation();
        userReputation.setUserId(userId);
        userReputation.setChatId(chatId);
        userReputation.setReputationValue(INTEGER_ZERO);
        userReputation.setUpdatedDatetime(Calendar.getInstance().getTime());
        userReputation.setUpdatedFrom(updatedFromId);
        return userReputation;
    }

}
