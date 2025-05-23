package com.telegram.rtb.command.keyword.delete;

import com.telegram.rtb.command.keyword.AbstractKeywordCommand;
import com.telegram.rtb.configuration.feature.FeatureConfiguration;
import com.telegram.rtb.model.message.BotApiMethodResponse;
import com.telegram.rtb.service.keyword.IKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.message.Message;

@Component("!del_minus_keyword")
public class DeleteMinusKeywordCommand extends AbstractKeywordCommand {

    @Autowired
    @Qualifier("minusKeywordService")
    private IKeywordService keywordService;

    @Autowired
    private FeatureConfiguration featureConfiguration;

    @Override
    public BotApiMethodResponse execute(Message message) {
        if (featureConfiguration.isFeatureKeywordsEnabled()) {
            return executeDeleteKeyword(message, (keyword, chatId) -> keywordService.deleteKeyword(keyword, chatId));
        }
        return null;
    }
}
