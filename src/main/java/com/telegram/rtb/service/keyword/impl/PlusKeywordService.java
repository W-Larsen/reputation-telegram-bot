package com.telegram.rtb.service.keyword.impl;

import com.telegram.rtb.model.domain.keyword.PlusKeyword;
import com.telegram.rtb.model.keyword.PlusKeywords;
import com.telegram.rtb.repository.keyword.PlusKeywordRepository;
import com.telegram.rtb.service.chat.IChatService;
import com.telegram.rtb.service.keyword.AbstractKeywordService;
import com.telegram.rtb.service.keyword.IKeywordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.telegram.rtb.transform.keyword.PlusKeywordTransformer.createPlusKeyword;

/**
 * Plus keyword service.
 *
 * @author Valentyn Korniienko
 */
@Service
@Slf4j
public class PlusKeywordService extends AbstractKeywordService implements IKeywordService {

    @Autowired
    private PlusKeywordRepository keywordRepository;
    @Autowired
    private IChatService chatService;

    @Override
    public PlusKeywords getKeywordsByChatId(Long chatId) {
        return getPlusKeywordsByChatId(chatId);
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
    @Transactional
    public void deleteAllKeywords(Long chatId) {
        checkChatId(chatId);

        keywordRepository.deleteAllByKeywordPkChatId(chatId);
    }

    @Override
    @Transactional
    public void deleteKeyword(String keyword, Long chatId) {
        checkChatId(chatId);

        keywordRepository.deleteKeywordByKeywordValueAndKeywordPkChatId(keyword, chatId);
    }
}
