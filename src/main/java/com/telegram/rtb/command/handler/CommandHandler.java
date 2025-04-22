package com.telegram.rtb.command.handler;

import com.telegram.rtb.command.Command;
import com.telegram.rtb.model.message.BotApiMethodResponse;
import com.telegram.rtb.service.keyword.extractor.KeywordInterpreter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.chat.Chat;
import org.telegram.telegrambots.meta.api.objects.message.Message;

import static com.telegram.rtb.util.BotApiMethodResponseCreator.createDefaultBotApiMethodResponse;

/**
 * Command handler.
 *
 * @author Valenyn Korniienko
 */
@Component
@Slf4j
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
