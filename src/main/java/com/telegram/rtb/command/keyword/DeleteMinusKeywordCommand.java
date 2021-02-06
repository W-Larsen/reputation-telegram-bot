package com.telegram.rtb.command.keyword;

import com.telegram.rtb.model.message.BotApiMethodResponse;
import com.telegram.rtb.service.keyword.IKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component("!del_minus_keyword")
public class DeleteMinusKeywordCommand extends AbstractKeywordCommand {

    @Autowired
    @Qualifier("minusKeywordService")
    private IKeywordService keywordService;

    @Override
    public BotApiMethodResponse execute(Message message) {
        return executeDeleteKeyword(message, (keyword, chatId) -> keywordService.deleteKeyword(keyword, chatId));
    }
}
