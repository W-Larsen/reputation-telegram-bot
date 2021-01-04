package com.telegram.rtb.service.keyword.impl;

import com.telegram.rtb.exception.NotFoundException;
import com.telegram.rtb.model.domain.keyword.MinusKeyword;
import com.telegram.rtb.model.keyword.MinusKeywords;
import com.telegram.rtb.repository.keyword.MinusKeywordRepository;
import com.telegram.rtb.service.chat.IChatService;
import com.telegram.rtb.service.keyword.IKeywordService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.telegram.rtb.transform.keyword.MinusKeywordTransformer.createPlusKeyword;
import static com.telegram.rtb.transform.keyword.MinusKeywordTransformer.transformMinusKeywords;

/**
 * Minus keyword service.
 *
 * @author Valentyn Korniinenko
 */
@Service
@Log4j2
public class MinusKeywordService implements IKeywordService {

    @Autowired
    private MinusKeywordRepository keywordRepository;
    @Autowired
    private IChatService chatService;

    @Override
    public MinusKeywords getKeywordsByChatId(Long chatId) {
        checkChatId(chatId);

        List<MinusKeyword> keywords = keywordRepository.findAllByKeywordPkChatId(chatId)
                .orElse(Collections.emptyList());
        return transformMinusKeywords(keywords, chatId);
    }

    @Override
    public void saveKeywords(List<String> keywords, Long chatId) {
        checkChatId(chatId);

        List<MinusKeyword> minusKeywords = keywords.stream()
                .map(keyword -> createPlusKeyword(keyword, chatId))
                .collect(Collectors.toList());
        keywordRepository.saveAll(minusKeywords);
    }

    @Override
    public void addKeyword(String keyword, Long chatId) {
        checkChatId(chatId);

        keywordRepository.save(createPlusKeyword(keyword, chatId));
    }

    @Override
    public void deleteAllKeywords(Long chatId) {
        checkChatId(chatId);

        keywordRepository.deleteAllByKeywordPkChatId(chatId);
    }

    @Override
    public void deleteKeyword(String keyword, Long chatId) {
        checkChatId(chatId);

        keywordRepository.deleteKeywordByKeywordValueAndKeywordPkChatId(keyword, chatId);
    }

    private void checkChatId(Long chatId) {
        if (Objects.isNull(chatService.getTelegramChatByChatId(chatId))) {
            String message = String.format("Telegram chat with such id %s does not exists in database.", chatId);
            log.error(message);
            throw new NotFoundException(message);
        }
    }
}
