package com.telegram.rtb.service.keyword;

import com.telegram.rtb.model.keyword.Keywords;

import java.util.List;

/**
 * Keyword service interface.
 *
 * @author Valentyn Korniienko
 */
public interface IKeywordService {

    /**
     * Get keywords by chat id.
     *
     * @param chatId the chat id
     * @return list of keywords
     */
    Keywords getKeywordsByChatId(Long chatId);

    /**
     * Save new keywords by chat id.
     *
     * @param keywords the keywords
     * @param chatId   the chat id
     */
    void saveKeywords(List<String> keywords, Long chatId);

    /**
     * Add keyword.
     *
     * @param keyword the keyword
     * @param chatId  the chat id
     */
    void addKeyword(String keyword, Long chatId);

    /**
     * Delete all keywords by chat id.
     *
     * @param chatId the chat id
     */
    void deleteAllKeywords(Long chatId);

    /**
     * Delete keyword by chat id.
     *
     * @param keyword the keyword
     * @param chatId  the chat id
     */
    void deleteKeyword(String keyword, Long chatId);

}
