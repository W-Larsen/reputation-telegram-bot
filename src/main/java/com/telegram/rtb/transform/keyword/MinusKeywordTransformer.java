package com.telegram.rtb.transform.keyword;

import com.telegram.rtb.model.domain.key.KeywordPk;
import com.telegram.rtb.model.domain.keyword.Keyword;
import com.telegram.rtb.model.domain.keyword.MinusKeyword;
import com.telegram.rtb.model.keyword.MinusKeywords;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Minus keyword transformer.
 */
public class MinusKeywordTransformer {

    /**
     * Transform minus keywords.
     *
     * @param keywords the keywords.
     * @param chatId   the chat id
     * @return minus keywords
     */
    public static MinusKeywords transformMinusKeywords(List<MinusKeyword> keywords, Long chatId) {
        return MinusKeywords.builder()
                .chatId(chatId)
                .keywordValues(keywords.stream().map(Keyword::getKeywordValue).collect(Collectors.toList()))
                .build();
    }

    /**
     * Create plus keywords.
     *
     * @param keyword the keyword
     * @param chatId  the chat id
     * @return minus keyword
     */
    public static MinusKeyword createPlusKeyword(String keyword, Long chatId) {
        return MinusKeyword.builder()
                .keywordPk(KeywordPk.builder().keywordId(UUID.randomUUID().toString()).chatId(chatId).build())
                .keywordValue(keyword)
                .build();
    }
}
