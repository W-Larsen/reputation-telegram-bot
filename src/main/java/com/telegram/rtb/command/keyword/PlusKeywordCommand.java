package com.telegram.rtb.command.keyword;

import com.telegram.rtb.model.message.BotApiMethodResponse;
import com.telegram.rtb.service.keyword.IKeywordService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

import static com.telegram.rtb.constants.Messages.KEYWORDS_WAS_ADDED_RU;

@Component("/!add_plus_keywords")
@Log4j2
public class PlusKeywordCommand extends AbstractKeywordCommand {

    @Autowired
    @Qualifier("plusKeywordService")
    private IKeywordService keywordService;

    @Override
    public BotApiMethodResponse execute(Message message) {
        return executeAddKeywords(message, (keyword, chatId) -> keywordService.saveKeywords(keyword, chatId));
    }



}
