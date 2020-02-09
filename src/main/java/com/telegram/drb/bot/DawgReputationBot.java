package com.telegram.drb.bot;

import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Dawg reputation bot implementation.
 *
 * @author Valentyn Korniienko
 */
public class DawgReputationBot extends TelegramLongPollingBot {

    @Value("${dawg.reputation.bot.token}")
    private String botToken;

    @Value("${dawg.reputation.bot.username}")
    private String botUsername;

    @Override
    public void onUpdateReceived(Update update) {

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
