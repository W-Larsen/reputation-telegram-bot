package com.telegram.rtb.service.keyword.extractor;

import com.telegram.rtb.model.keyword.AllKeywords;
import com.telegram.rtb.model.keyword.KeywordFunction;
import com.telegram.rtb.service.keyword.IKeywordService;
import com.telegram.rtb.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static com.telegram.rtb.model.keyword.KeywordFunction.MINUS;
import static com.telegram.rtb.model.keyword.KeywordFunction.PLUS;

/**
 * Keyword interpreter.
 *
 * @author Valentyn Korniienko
 */
@Component
public class KeywordInterpreter {

    @Autowired
    @Qualifier("plusKeywordService")
    private IKeywordService keywordService;

    @Value("${telegram.reputation.bot.username}")
    private String botUsername;

    /**
     * Interpret keyword.
     *
     * @param chatId      the chat id
     * @param commandText the command text
     * @return managing command
     */
    public String interpretKeyword(Long chatId, String commandText) {
        commandText = MessageUtils.trimMessageText(commandText, botUsername);
        AllKeywords keywords = keywordService.getAllKeywords(chatId);
        Map<KeywordFunction, List<String>> keywordFunctionValues = getKeywordFunctionValues(keywords);
        return getPossibleManagingCommand(commandText, keywordFunctionValues);
    }

    private Map<KeywordFunction, List<String>> getKeywordFunctionValues(AllKeywords keywords) {
        Map<KeywordFunction, List<String>> keywordFunctionsMap = new EnumMap<>(KeywordFunction.class);
        keywordFunctionsMap.put(PLUS, keywords.getPlusKeywords().getKeywordValues());
        keywordFunctionsMap.put(MINUS, keywords.getMinusKeywords().getKeywordValues());
        return keywordFunctionsMap;
    }

    private String getPossibleManagingCommand(String commandText, Map<KeywordFunction, List<String>> relatedMessages) {
        return relatedMessages.entrySet().stream()
                .filter(entry -> entry.getValue().stream().anyMatch(commandText::equalsIgnoreCase))
                .map(entry -> entry.getKey().getValue())
                .findFirst()
                .orElse(commandText);
    }

}
