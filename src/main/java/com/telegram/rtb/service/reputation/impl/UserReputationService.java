package com.telegram.rtb.service.reputation.impl;

import com.telegram.rtb.exception.NotFoundException;
import com.telegram.rtb.model.domain.TelegramChat;
import com.telegram.rtb.model.domain.TelegramUser;
import com.telegram.rtb.model.domain.UserReputation;
import com.telegram.rtb.model.domain.key.UserReputationPk;
import com.telegram.rtb.model.rest.reputation.ReputationRequest;
import com.telegram.rtb.model.rest.reputation.ReputationResponse;
import com.telegram.rtb.repository.UserReputationRepository;
import com.telegram.rtb.service.chat.IChatService;
import com.telegram.rtb.service.reputation.IUserReputationService;
import com.telegram.rtb.service.user.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.telegram.rtb.constants.Messages.DELAY_MESSAGE_RU;
import static com.telegram.rtb.util.UserUtils.getFullName;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

/**
 * User reputation service implementation.
 *
 * @author Valentyn Korniienko
 */
@Service
public class UserReputationService implements IUserReputationService {

    @Autowired
    private UserReputationRepository userReputationRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private IChatService chatService;

    @Value("${telegram.default.delay}")
    private int defaultDelay;
    @Value("${telegram.reputation.ignore.list.ids}")
    private List<String> ignoreList;

    @Override
    public UserReputation createUserReputation(UserReputation userReputation) {
        return userReputationRepository.save(userReputation);
    }

    @Override
    public Optional<UserReputation> findByUserIdAndChatId(Integer userId, Long chatId) {
        return userReputationRepository.findByUserIdAndChatId(userId, chatId);
    }

    @Override
    @Transactional
    public String manageUserReputation(User fromBy, User repliedTo, Chat chat, Consumer<UserReputation> action, String actionMessage) {
        UserReputation reputationFromBy = userReputationRepository.findByUserIdAndChatId(fromBy.getId(), chat.getId())
                .orElseGet(() -> createUserRelation(fromBy, chat));
        UserReputation reputationRepliedTo = userReputationRepository.findByUserIdAndChatId(repliedTo.getId(), chat.getId())
                .orElseGet(() -> createUserRelation(repliedTo, chat));

        if (reputationFromBy.getUserReputationPk().getUserId().equals(reputationRepliedTo.getUserReputationPk().getUserId())) {
            return StringUtils.EMPTY;
        }

        if (!isValidUpdatedTime(fromBy, chat, reputationRepliedTo)) {
            return DELAY_MESSAGE_RU;
        }

        if (ignoreList.contains(reputationRepliedTo.getUserReputationPk().getUserId().toString())) {
            return String.format("%s (%s) НЕ %s репутацию %s (%s)",
                    getFullName(fromBy), reputationFromBy.getReputationValue(), actionMessage,
                    getFullName(repliedTo), reputationRepliedTo.getReputationValue());
        }

        action.accept(reputationRepliedTo);

        UserReputation actualRepliedToReputation = userReputationRepository.findByUserIdAndChatId(repliedTo.getId(), chat.getId())
                .orElseThrow(() -> new NotFoundException(String.format("User with such user id %s and chat id %s does not exist", repliedTo.getId(), chat.getId())));

        return String.format("%s (%s) %s репутацию %s (%s)",
                getFullName(fromBy), reputationFromBy.getReputationValue(), actionMessage,
                getFullName(repliedTo), actualRepliedToReputation.getReputationValue());
    }

    private UserReputation createUserRelation(User user, Chat chat) {
        userService.addTelegramUser(toTelegramUser(user));
        chatService.addTelegramChat(toTelegramChat(chat));
        return userReputationRepository.saveAndFlush(createUserReputationClass(user.getId(), chat.getId(), user.getId()));
    }

    private boolean isValidUpdatedTime(User fromBy, Chat actualChat, UserReputation repliedTo) {
        if (isTheSameUser(fromBy, repliedTo) && isTheSameChat(actualChat, repliedTo)) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime updatedDateTime = repliedTo.getUpdatedDateTime().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            Duration duration = Duration.between(now, updatedDateTime);
            // convert to seconds
            long diff = Math.abs(duration.toMillis()) / 1000;
            return diff > defaultDelay;
        }
        return true;
    }

    private boolean isTheSameUser(User fromBy, UserReputation repliedTo) {
        return (repliedTo.getUpdatedFromId().equals(fromBy.getId()));
    }

    private boolean isTheSameChat(Chat actualChat, UserReputation repliedTo) {
        return actualChat.getId().equals(repliedTo.getTelegramChat().getChatId());
    }

    private TelegramUser toTelegramUser(User user) {
        return TelegramUser.builder()
                .userId(user.getId())
                .userName(getUserName(user))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    private String getUserName(User user) {
        return StringUtils.isEmpty(user.getUserName()) ? user.getFirstName() : user.getFirstName();
    }

    private TelegramChat toTelegramChat(Chat chat) {
        return TelegramChat.builder()
                .chatId(chat.getId())
                .chatName(chat.getTitle())
                .build();
    }

    @Override
    public void increaseUserReputation(UserReputation userReputation, User updatedFrom) {
        userReputationRepository.increaseUserReputation(updatedFrom.getId(), userReputation.getUserReputationPk().getUserId(), userReputation.getUserReputationPk().getChatId());
    }

    @Override
    public void reduceUserReputation(UserReputation userReputation, User updatedFrom) {
        userReputationRepository.reduceUserReputation(updatedFrom.getId(), userReputation.getUserReputationPk().getUserId(), userReputation.getUserReputationPk().getChatId());
    }

    @Override
    public List<UserReputation> findAll(long limit, Sort sort) {
        return userReputationRepository.findAll(sort).stream()
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public ReputationResponse updateUserReputation(ReputationRequest reputationRequest) {
        userReputationRepository.updateUserReputation(reputationRequest.getReputation(), reputationRequest.getUserId(), reputationRequest.getChatId());
        return createReputationResponse(reputationRequest);
    }

    private UserReputation createUserReputationClass(Integer userId, Long chatId, Integer updatedFromId) {
        return UserReputation.builder()
                .userReputationPk(UserReputationPk.builder()
                        .userId(userId)
                        .chatId(chatId)
                        .build())
                .reputationValue(INTEGER_ZERO)
                .updatedDateTime(ZonedDateTime.now())
                .updatedFromId(updatedFromId)
                .build();
    }

    private ReputationResponse createReputationResponse(ReputationRequest reputationRequest) {
        return ReputationResponse.builder()
                .userId(reputationRequest.getUserId())
                .chatId(reputationRequest.getChatId())
                .reputation(reputationRequest.getReputation())
                .build();
    }

}
