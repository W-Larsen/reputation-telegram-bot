package com.telegram.rtb.command.keyword.delete;

import com.telegram.rtb.command.keyword.AbstractKeywordCommand;
import com.telegram.rtb.model.message.BotApiMethodResponse;
import com.telegram.rtb.service.keyword.IKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component("!del_all_minus_keywords")
public class DeleteAllMinusKeywordsCommand extends AbstractKeywordCommand {

    @Autowired
    @Qualifier("minusKeywordService")
    private IKeywordService keywordService;

    @Override
    public BotApiMethodResponse execute(Message message) {
        return executeDeleteKeywords(message, (chatId) -> keywordService.deleteAllKeywords(chatId));
    }
}
