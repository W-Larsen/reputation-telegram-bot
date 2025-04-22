package com.telegram.rtb.service.keyword;

import com.telegram.rtb.exception.NotFoundException;
import com.telegram.rtb.model.domain.keyword.MinusKeyword;
import com.telegram.rtb.model.domain.keyword.PlusKeyword;
import com.telegram.rtb.model.keyword.AllKeywords;
import com.telegram.rtb.model.keyword.MinusKeywords;
import com.telegram.rtb.model.keyword.PlusKeywords;
import com.telegram.rtb.repository.keyword.MinusKeywordRepository;
import com.telegram.rtb.repository.keyword.PlusKeywordRepository;
import com.telegram.rtb.service.chat.IChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.telegram.rtb.transform.keyword.MinusKeywordTransformer.transformMinusKeywords;
import static com.telegram.rtb.transform.keyword.PlusKeywordTransformer.transformPlusKeywords;

/**
 * Abstract keyword service
 */
@Slf4j
public abstract class AbstractKeywordService implements IKeywordService {

    @Autowired
    private PlusKeywordRepository plusKeywordRepository;
    @Autowired
    private MinusKeywordRepository minusKeywordRepository;
    @Autowired
    private IChatService chatService;

    @Override
    public AllKeywords getAllKeywords(Long chatId) {
        return AllKeywords.builder()
                .chatId(chatId)
                .plusKeywords(getPlusKeywordsByChatId(chatId))
                .minusKeywords(getMinusKeywordsByChatId(chatId))
                .build();
    }

    protected PlusKeywords getPlusKeywordsByChatId(Long chatId) {
        checkChatId(chatId);

        List<PlusKeyword> keywords = plusKeywordRepository.findAllByKeywordPkChatId(chatId)
                .orElse(Collections.emptyList());
        return transformPlusKeywords(keywords, chatId);
    }

    protected MinusKeywords getMinusKeywordsByChatId(Long chatId) {
        checkChatId(chatId);

        List<MinusKeyword> keywords = minusKeywordRepository.findAllByKeywordPkChatId(chatId)
                .orElse(Collections.emptyList());
        return transformMinusKeywords(keywords, chatId);
    }

    protected void checkChatId(Long chatId) {
        if (Objects.isNull(chatService.getTelegramChatByChatId(chatId))) {
            String message = String.format("Telegram chat with such id %s does not exists in database.", chatId);
            log.error(message);
            throw new NotFoundException(message);
        }
    }
}
