package com.telegram.drb.bot;

import com.telegram.drb.command.handler.CommandHandler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
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
            String responseText = commandHandler.handleMessage(message);
            if (StringUtils.isNotEmpty(responseText)) {
                SendMessage response = new SendMessage();
                Long chatId = message.getChatId();
                response.setChatId(chatId);
                response.setText(responseText);
                try {
                    execute(response);
                    LOGGER.info("Sent message \"{}\" to {}", responseText, message.getChat().getFirstName());
                } catch (TelegramApiException e) {
                    LOGGER.error("Failed to send message \"{}\" to {} due to error: {}", responseText, chatId, e.getMessage());
                }
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
