package com.telegram.rtb.command.handler;

import com.telegram.rtb.command.Command;
import com.telegram.rtb.model.message.BotApiMethodResponse;
import com.telegram.rtb.service.keyword.extractor.KeywordInterpreter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;

import static com.telegram.rtb.util.BotApiMethodResponseCreator.createDefaultBotApiMethodResponse;

/**
 * Command handler.
 *
 * @author Valenyn Korniienko
 */
@Component
@Log4j2
public class CommandHandler {

    @Autowired
    private CommandExtractor commandExtractor;
    @Autowired
    private KeywordInterpreter keywordInterpreter;

    /**
     * Handle message.
     *
     * @param message the message.
     * @return command response
     */
    public BotApiMethodResponse handleMessage(Message message) {
        String commandText = message.getText();
        if (StringUtils.isNotEmpty(commandText)) {
            Chat chat = message.getChat();
            commandText = keywordInterpreter.interpretKeyword(chat.getId(), commandText);
            Command command = commandExtractor.getCommand(commandText, chat, message.getFrom());
            if (command != null) {
                return command.execute(message);
            }
        }
        return createDefaultBotApiMethodResponse();
    }

}
