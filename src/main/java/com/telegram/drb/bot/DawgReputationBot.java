package com.telegram.drb.bot;

import com.telegram.drb.command.handler.CommandHandler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Dawg reputation bot implementation.
 *
 * @author Valentyn Korniienko
 */
@Component
public class DawgReputationBot extends TelegramLongPollingBot {

    private static final Logger LOGGER = LoggerFactory.getLogger(DawgReputationBot.class);

    @Value("${dawg.reputation.bot.token}")
    private String botToken;

    @Value("${dawg.reputation.bot.username}")
    private String botUsername;

    @Autowired
    private CommandHandler commandHandler;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            BotApiMethod<?> response = commandHandler.handleMessage(message);
            try {
                if (response != null) {
                    if (response instanceof SendMessage) {
                        SendMessage messageResponse = (SendMessage) response;
                        if (StringUtils.isNotEmpty(messageResponse.getText())) {
                            execute(messageResponse);
                            LOGGER.info("Sent message \"{}\" to {} chat ",
                                    messageResponse.getText(), message.getChat().getTitle());
                        }
                    }
                    if (response instanceof DeleteMessage) {
                        DeleteMessage deleteMessageResponse = (DeleteMessage) response;
                        if (deleteMessageResponse.getMessageId() != null) {
                            execute(deleteMessageResponse);
                            LOGGER.info("Message with such id {} was successfully deleted",
                                    deleteMessageResponse.getMessageId());
                        }
                    }

                }
            } catch (TelegramApiException e) {
                LOGGER.error("Failed to send message due to error: {}", e.getMessage());
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
