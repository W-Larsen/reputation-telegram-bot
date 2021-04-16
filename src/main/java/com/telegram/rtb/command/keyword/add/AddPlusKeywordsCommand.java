package com.telegram.rtb.command.keyword.add;

import com.telegram.rtb.command.keyword.AbstractKeywordCommand;
import com.telegram.rtb.configuration.FeatureConfiguration;
import com.telegram.rtb.model.message.BotApiMethodResponse;
import com.telegram.rtb.service.keyword.IKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component("!add_plus_keywords")
public class AddPlusKeywordsCommand extends AbstractKeywordCommand {

    @Autowired
    @Qualifier("plusKeywordService")
    private IKeywordService keywordService;

    @Autowired
    private FeatureConfiguration featureConfiguration;

    @Override
    public BotApiMethodResponse execute(Message message) {
        if (featureConfiguration.isFeatureKeywordsEnabled()) {
            return executeAddKeywords(message, (keyword, chatId) -> keywordService.saveKeywords(keyword, chatId));
        }
        return null;
    }
}