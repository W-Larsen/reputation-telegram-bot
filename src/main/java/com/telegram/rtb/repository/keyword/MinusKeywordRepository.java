package com.telegram.rtb.repository.keyword;

import com.telegram.rtb.model.domain.key.KeywordPk;
import com.telegram.rtb.model.domain.keyword.MinusKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Minus keyword repository.
 *
 * @author Valentyn Korniienko
 */
@Repository
public interface MinusKeywordRepository extends JpaRepository<MinusKeyword, KeywordPk> {

    Optional<List<MinusKeyword>> findAllByKeywordPkChatId(Long chatId);

    void deleteAllByKeywordPkChatId(Long chatId);

    void deleteKeywordByKeywordValueAndKeywordPkChatId(String keyword, Long chatId);

}
