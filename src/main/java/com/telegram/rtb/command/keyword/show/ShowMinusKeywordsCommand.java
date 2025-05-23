package com.telegram.rtb.command.keyword.show;

import com.telegram.rtb.command.keyword.AbstractKeywordCommand;
import com.telegram.rtb.configuration.feature.FeatureConfiguration;
import com.telegram.rtb.model.message.BotApiMethodResponse;
import com.telegram.rtb.service.keyword.IKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.message.Message;

@Component("!show_minus_keywords")
public class ShowMinusKeywordsCommand extends AbstractKeywordCommand {

    @Autowired
    @Qualifier("minusKeywordService")
    private IKeywordService keywordService;

    @Autowired
    private FeatureConfiguration featureConfiguration;

    @Override
    public BotApiMethodResponse execute(Message message) {
        if (featureConfiguration.isFeatureKeywordsEnabled()) {
            return executeShowPlusKeywords(message, (chatId) -> keywordService.getKeywordsByChatId(chatId));
        }
        return null;
    }

}
