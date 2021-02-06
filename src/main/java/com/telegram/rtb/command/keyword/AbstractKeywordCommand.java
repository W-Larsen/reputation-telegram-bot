package com.telegram.rtb.command.keyword;

import com.telegram.rtb.command.AbstractCommand;
import com.telegram.rtb.command.Command;
import com.telegram.rtb.exception.BadRequestException;
import com.telegram.rtb.model.keyword.Keywords;
import com.telegram.rtb.model.message.BotApiMethodResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.telegram.rtb.model.domain.ParseMode.MARKDOWN_V2;
import static com.telegram.rtb.model.message.MethodName.ADD_KEYWORDS;
import static com.telegram.rtb.model.message.MethodName.DELETE_KEYWORD;
import static com.telegram.rtb.model.message.MethodName.DELETE_KEYWORDS;
import static com.telegram.rtb.model.message.MethodName.SHOW_KEYWORDS;
import static com.telegram.rtb.util.MessageUtils.trimCommandText;

/**
 * Abstract keyword command class.
 */
@Log4j2
public abstract class AbstractKeywordCommand extends AbstractCommand implements Command {

    /**
     * Method for adding one or more (plus/minus) keywords by chat id.
     *
     * @param message the telegram message
     * @param action  the action from plus/minus keyword service
     * @return bot api method response with message
     */
    protected BotApiMethodResponse executeAddKeywords(Message message, BiConsumer<List<String>, Long> action) {
        List<String> keywords = getKeywords(trimCommandText(message.getText()));
        checkKeywords(keywords);
        action.accept(keywords, message.getChatId());
        return createBotApiMethodResponse(List.of(createResponseSendMessage(message, generateAddKeywordsResponseText(keywords))), ADD_KEYWORDS);
    }

    /**
     * Method for deleting only one keyword from plus/minus keywords by keyword and chat id.
     *
     * @param message the telegram message
     * @param action  the action from plus/minus keyword service
     * @return bot api method response with message
     */
    protected BotApiMethodResponse executeDeleteKeyword(Message message, BiConsumer<String, Long> action) {
        String keyword = trimCommandText(message.getText());
        checkKeyword(keyword);
        action.accept(keyword, message.getChatId());
        return createBotApiMethodResponse(List.of(createResponseSendMessage(message, generateDeleteKeywordResponseText(keyword))), DELETE_KEYWORD);
    }

    /**
     * Method for deleting all (plus/minus) keywords by chat id.
     *
     * @param message the telegram message
     * @param action  the action from plus/minus keyword service
     * @return bot api method response with message
     */
    protected BotApiMethodResponse executeDeleteKeywords(Message message, Consumer<Long> action) {
        action.accept(message.getChatId());
        return createBotApiMethodResponse(List.of(createResponseSendMessage(message, generateDeleteKeywordsResponseText())), DELETE_KEYWORDS);
    }

    /**
     * Method for returning all (plus/minus) keywords by chat id.
     *
     * @param message the telegram message
     * @param action  the action from plus/minus keyword service
     * @return bot api method response with all (plus/minus) keywords
     */
    protected BotApiMethodResponse executeShowPlusKeywords(Message message, Function<Long, Keywords> action) {
        Keywords keywords = action.apply(message.getChatId());
        return createBotApiMethodResponse(List.of(createResponseSendMessage(message, generateShowKeywordsResponseText(keywords.getKeywordValues()))), SHOW_KEYWORDS);
    }

    private List<String> getKeywords(String text) {
        return Arrays.stream(text.split(",|\\s+"))
                .filter(StringUtils::isNotEmpty)
                .collect(Collectors.toList());
    }

    private void checkKeywords(List<String> keywords) {
        if (CollectionUtils.isEmpty(keywords)) {
            String errMes = "Empty request message";
            log.error(errMes);
            throw new BadRequestException(errMes);
        }
    }

    private void checkKeyword(String keyword) {
        if (StringUtils.isEmpty(keyword)) {
            String errMes = "Empty keyword";
            log.error(errMes);
            throw new BadRequestException(errMes);
        }
    }

    private SendMessage createResponseSendMessage(Message message, String responseText) {
        SendMessage messageResponse = createDefaultSendMessageResponse(message.getChatId(), responseText);
        messageResponse.setParseMode(MARKDOWN_V2.getValue());
        return messageResponse;
    }

    private String generateAddKeywordsResponseText(List<String> keywords) {
        return keywords.size() > 1 ? "Ключевые слова успешно добавлены\\." : "Ключевое слово добавлено\\.";
    }

    private String generateDeleteKeywordResponseText(String keyword) {
        return String.format("Ключевое слово '%s' успешно удалено\\.", keyword);
    }

    private String generateDeleteKeywordsResponseText() {
        return "Все ключевые слова успешно удалены\\.";
    }

    private String generateShowKeywordsResponseText(List<String> keywords) {
        return "*Ключевые слова*: " + String.join(", ", keywords);
    }

}
