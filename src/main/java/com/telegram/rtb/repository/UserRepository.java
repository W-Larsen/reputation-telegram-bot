package com.telegram.rtb.repository;

import com.telegram.rtb.model.domain.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * User repository interface.
 *
 * @author Valentyn Korniienko
 */
@Repository
public interface UserRepository extends JpaRepository<TelegramUser, Long> {

    /**
     * Find user by user id.
     *
     * @param userId the user id
     * @return user
     */
    @Query(value = "SELECT user FROM TelegramUser user WHERE user.userId = :userId")
    TelegramUser findUserById(Integer userId);
}
