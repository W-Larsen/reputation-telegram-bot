package com.telegram.rtb.command.keyword;

import com.telegram.rtb.command.AbstractCommand;
import com.telegram.rtb.command.Command;
import com.telegram.rtb.exception.BadRequestException;
import com.telegram.rtb.model.message.BotApiMethodResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import static com.telegram.rtb.model.domain.ParseMode.MARKDOWN_V2;
import static com.telegram.rtb.model.message.MethodName.PLUS_KEYWORD;
import static com.telegram.rtb.util.MessageUtils.trimCommandText;

/**
 * Abstract keyword command class.
 */
@Log4j2
public abstract class AbstractKeywordCommand extends AbstractCommand implements Command {

    protected BotApiMethodResponse executeAddKeywords(Message message, BiConsumer<List<String>, Long> action) {
        List<String> keywords = getKeywords(trimCommandText(message.getText()));
        checkKeywords(keywords);
        action.accept(keywords, message.getChatId());
        return createBotApiMethodResponse(Collections.singletonList(createResponseSendMessage(message, generateResponseText(keywords))), PLUS_KEYWORD);
    }

    protected List<String> getKeywords(String text) {
        return Arrays.stream(text.split(",|\\s+"))
                .filter(StringUtils::isNotEmpty)
                .collect(Collectors.toList());
    }

    protected void checkKeywords(List<String> keywords) {
        if (CollectionUtils.isEmpty(keywords)) {
            String errMes = "Empty request message";
            log.error(errMes);
            throw new BadRequestException(errMes);
        }
    }

    protected SendMessage createResponseSendMessage(Message message, String responseText) {
        SendMessage messageResponse = createDefaultSendMessageResponse(message.getChatId(), responseText);
        messageResponse.setParseMode(MARKDOWN_V2.getValue());
        return messageResponse;
    }

    private String generateResponseText(List<String> keywords) {
        return keywords.size() > 1 ? "Ключевые слова успешно добавлены!" : "Ключевое слово добавлено!";
    }

}
