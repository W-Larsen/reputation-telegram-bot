package com.telegram.rtb.bot;

import com.telegram.rtb.bot.sender.MessageSender;
import com.telegram.rtb.command.handler.CommandHandler;
import com.telegram.rtb.exception.TelegramApiBadRequestException;
import com.telegram.rtb.model.message.BotApiMethodResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatAdministrators;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.Function;

import static com.telegram.rtb.model.message.MethodName.GET_CHAT_ADMINISTRATORS;

/**
 * Telegram reputation bot implementation.
 *
 * @author Valentyn Korniienko
 */
@Component
@Log4j2
public class TelegramReputationBot extends TelegramLongPollingBot {

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
            } catch (TelegramApiRequestException e) {
                log.error("Failed to deserialize response. Api response: {}. Error: {}", e.getApiResponse(), e.getMessage());
                throw new TelegramApiBadRequestException(e.getMessage(), e.getApiResponse(), e.getErrorCode());
            } catch (TelegramApiException e) {
                log.error("Failed to execute message due to error: {}", e.getMessage());
                throw new RuntimeException(e);
            }
        };
    }

    @Scheduled(cron = "${telegram.populate.administrators.scheduled.cron}")
    public void populateChatAdministrators() {
        messageSender.sendMessage(new GetChatAdministrators(), GET_CHAT_ADMINISTRATORS, executeMessage());
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
