package com.telegram.rtb.command.keyword.show;

import com.telegram.rtb.command.keyword.AbstractKeywordCommand;
import com.telegram.rtb.model.message.BotApiMethodResponse;
import com.telegram.rtb.service.keyword.IKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component("!show_plus_keywords")
public class ShowPlusKeywordsCommand extends AbstractKeywordCommand {

    @Autowired
    @Qualifier("plusKeywordService")
    private IKeywordService keywordService;

    @Override
    public BotApiMethodResponse execute(Message message) {
        return executeShowPlusKeywords(message, (chatId) -> keywordService.getKeywordsByChatId(chatId));
    }

}
