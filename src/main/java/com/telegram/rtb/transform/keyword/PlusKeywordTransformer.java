package com.telegram.rtb.transform.keyword;

import com.telegram.rtb.model.domain.key.KeywordPk;
import com.telegram.rtb.model.domain.keyword.Keyword;
import com.telegram.rtb.model.domain.keyword.PlusKeyword;
import com.telegram.rtb.model.keyword.PlusKeywords;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Plus keyword transformer.
 */
public class PlusKeywordTransformer {

    /**
     * Transform plus keywords.
     *
     * @param keywords the keywords
     * @param chatId   the chat id
     * @return plus keywords
     */
    public static PlusKeywords transformPlusKeywords(List<PlusKeyword> keywords, Long chatId) {
        return PlusKeywords.builder()
                .chatId(chatId)
                .keywordValues(keywords.stream().map(Keyword::getKeywordValue).collect(Collectors.toList()))
                .build();
    }

    /**
     * Create plus keywords.
     *
     * @param keyword the keyword
     * @param chatId  the chat id
     * @return plus keyword
     */
    public static PlusKeyword createPlusKeyword(String keyword, Long chatId) {
        return PlusKeyword.builder()
                .keywordPk(KeywordPk.builder().keywordId(UUID.randomUUID().toString()).chatId(chatId).build())
                .keywordValue(keyword)
                .build();
    }

}
