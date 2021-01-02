package com.telegram.rtb.repository.keyword;

import com.telegram.rtb.model.domain.key.KeywordPk;
import com.telegram.rtb.model.domain.keyword.PlusKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Plus keyword repository.
 *
 * @author Valentyn Korniienko
 */
@Repository
public interface PlusKeywordRepository extends JpaRepository<PlusKeyword, KeywordPk> {

    Optional<List<PlusKeyword>> findAllByKeywordPkChatId(Long chatId);

    void deleteAllByKeywordPkChatId(Long chatId);

    void deleteKeywordByKeywordValueAndKeywordPkChatId(String keyword, Long chatId);

}
