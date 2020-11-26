package com.telegram.rtb.repository;

import com.telegram.rtb.model.domain.UserReputation;
import com.telegram.rtb.model.domain.key.UserReputationPk;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * User reputation repository interface.
 *
 * @author Valentyn Korniienko
 */
public interface UserReputationRepository extends JpaRepository<UserReputation, UserReputationPk> {

    /**
     * Find user reputation by user id and chat id.
     *
     * @param userId the user id
     * @param chatId the chat id
     * @return user reputation
     */
    @Query(value = "SELECT rep FROM UserReputation rep " +
            "WHERE rep.userReputationPk.userId = :userId " +
            "AND rep.userReputationPk.chatId = :chatId")
    Optional<UserReputation> findByUserIdAndChatId(Integer userId, Long chatId);

    /**
     * Increase user reputation.
     *
     * @param updatedFromId the updated from id
     * @param userId        the user id
     * @param chatId        the chat id
     */
    @Modifying(clearAutomatically = true)
    @Query("UPDATE UserReputation rep SET " +
            "rep.reputationValue = rep.reputationValue + 1, " +
            "rep.updatedDateTime = CURRENT_TIMESTAMP, " +
            "rep.updatedFromId = :updatedFromId " +
            "WHERE rep.userReputationPk.userId = :userId " +
            "AND rep.userReputationPk.chatId = :chatId")
    void increaseUserReputation(Integer updatedFromId, Integer userId, Long chatId);

    /**
     * Reduce user reputation.
     *
     * @param updatedFromId the updated from id
     * @param userId        the user id
     * @param chatId        the chat id
     */
    @Modifying(clearAutomatically = true)
    @Query("UPDATE UserReputation rep SET " +
            "rep.reputationValue = rep.reputationValue - 1, " +
            "rep.updatedDateTime = CURRENT_TIMESTAMP, " +
            "rep.updatedFromId = :updatedFromId " +
            "WHERE rep.userReputationPk.userId = :userId " +
            "AND rep.userReputationPk.chatId = :chatId")
    void reduceUserReputation(Integer updatedFromId, Integer userId, Long chatId);

    /**
     * Finds all user reputations.
     *
     * @param sort the sort
     * @return list of user reputations
     */
    @Override
    List<UserReputation> findAll(Sort sort);

    /**
     * Update reputation value.
     *
     * @param userId          the user id
     * @param chatId          the chat id
     * @param reputationValue the reputation value
     */
    @Modifying(clearAutomatically = true)
    @Query("UPDATE UserReputation rep SET " +
            "rep.reputationValue = :reputationValue " +
            "WHERE rep.userReputationPk.userId = :userId " +
            "AND rep.userReputationPk.chatId = :chatId")
    void updateUserReputation(Integer reputationValue, Integer userId, Long chatId);
}
