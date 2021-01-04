package com.telegram.rtb.service.keyword.impl;

import com.telegram.rtb.exception.NotFoundException;
import com.telegram.rtb.model.domain.keyword.PlusKeyword;
import com.telegram.rtb.model.keyword.Keywords;
import com.telegram.rtb.model.keyword.PlusKeywords;
import com.telegram.rtb.repository.keyword.PlusKeywordRepository;
import com.telegram.rtb.service.chat.IChatService;
import com.telegram.rtb.service.keyword.IKeywordService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.telegram.rtb.transform.keyword.PlusKeywordTransformer.createPlusKeyword;
import static com.telegram.rtb.transform.keyword.PlusKeywordTransformer.transformPlusKeywords;

/**
 * Plus keyword service.
 *
 * @author Valentyn Korniienko
 */
@Service
@Log4j2
public class PlusKeywordService implements IKeywordService {

    @Autowired
    private PlusKeywordRepository keywordRepository;
    @Autowired
    private IChatService chatService;

    @Override
    public PlusKeywords getKeywordsByChatId(Long chatId) {
        checkChatId(chatId);
        List<PlusKeyword> keywords = keywordRepository.findAllByKeywordPkChatId(chatId)
                .orElse(Collections.emptyList());
        return transformPlusKeywords(keywords, chatId);
    }

    @Override
    public void saveKeywords(List<String> keywords, Long chatId) {
        checkChatId(chatId);

        List<PlusKeyword> plusKeywords = keywords.stream()
                .map(keyword -> createPlusKeyword(keyword, chatId))
                .collect(Collectors.toList());
        keywordRepository.saveAll(plusKeywords);
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
