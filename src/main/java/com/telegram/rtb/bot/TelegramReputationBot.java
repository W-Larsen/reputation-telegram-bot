package com.telegram.rtb.bot;

import com.telegram.rtb.bot.sender.MessageSender;
import com.telegram.rtb.command.handler.CommandHandler;
import com.telegram.rtb.model.message.BotApiMethodResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.Function;

/**
 * Telegram reputation bot implementation.
 *
 * @author Valentyn Korniienko
 */
@Component
public class TelegramReputationBot extends TelegramLongPollingBot {

    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramReputationBot.class);

    @Value("${telegram.reputation.bot.token}")
    private String botToken;

    @Value("${telegram.reputation.bot.username}")
    private String botUsername;

    @Autowired
    private CommandHandler commandHandler;
    @Autowired
    private MessageSender messageSender;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            BotApiMethodResponse botApiMethodsResponse = commandHandler.handleMessage(message);
            if (!Objects.isNull(botApiMethodsResponse)) {
                for (BotApiMethod<?> response : botApiMethodsResponse.getBotApiMethods()) {
                    messageSender.sendMessage(response, botApiMethodsResponse.getMethodName(), executeMessage());
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private <T extends Serializable> Function<BotApiMethod<?>, T> executeMessage() {
        return (BotApiMethod<?> botApiMethod) -> {
            try {
                return (T) execute(botApiMethod);
            } catch (TelegramApiException e) {
                LOGGER.error("Failed to send message due to error: {}", e.getMessage());
                throw new RuntimeException(e);
            }
        };
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
