package com.telegram.rtb.command.keyword;

import com.telegram.rtb.model.message.BotApiMethodResponse;
import com.telegram.rtb.service.keyword.IKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component("!add_minus_keywords")
public class AddMinusKeywordsCommand extends AbstractKeywordCommand {

    @Autowired
    @Qualifier("minusKeywordService")
    private IKeywordService keywordService;

    @Override
    public BotApiMethodResponse execute(Message message) {
        return executeAddKeywords(message, (keyword, chatId) -> keywordService.saveKeywords(keyword, chatId));
    }
}
