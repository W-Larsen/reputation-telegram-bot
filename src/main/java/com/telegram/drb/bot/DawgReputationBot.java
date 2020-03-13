package com.telegram.drb.bot;

import com.telegram.drb.command.handler.CommandHandler;
import com.telegram.drb.model.message.BotApiMethodResponse;
import com.telegram.drb.model.message.MethodName;
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

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.function.BiConsumer;

import static com.telegram.drb.model.message.MethodName.MANAGE_REPUTATION;

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
    @Autowired
    private Queue<Message> messageQueue;

    private Map<String, BiConsumer<BotApiMethod<?>, MethodName>> botApiMethodsExecute;

    @PostConstruct
    public void init() {
        botApiMethodsExecute = new HashMap<>();
        botApiMethodsExecute.put("sendmessage", this::executeSendMessage);
        botApiMethodsExecute.put("deleteMessage", this::executeDeleteMessage);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            BotApiMethodResponse botApiMethodsResponse = commandHandler.handleMessage(message);
            botApiMethodsResponse.getBotApiMethods()
                    .forEach(response -> botApiMethodsExecute.get(response.getMethod())
                            .accept(response, botApiMethodsResponse.getMethodName()));
        }
    }

    private void executeSendMessage(BotApiMethod<?> botApiMethod, MethodName methodName) {
        try {
            SendMessage sendMessageResponse = (SendMessage) botApiMethod;
            if (StringUtils.isNotEmpty(sendMessageResponse.getText())) {
                Message message = execute(sendMessageResponse);
                if (methodName.equals(MANAGE_REPUTATION)) {
                    messageQueue.add(message);
                }
                LOGGER.info("Sent message \"{}\" to {} chat ", sendMessageResponse.getText(), sendMessageResponse.getChatId());
            }
        } catch (TelegramApiException e) {
            LOGGER.error("Failed to send message due to error: {}", e.getMessage());
        }
    }

    private void executeDeleteMessage(BotApiMethod<?> botApiMethod, MethodName methodName) {
        try {
            DeleteMessage deleteMessageResponse = (DeleteMessage) botApiMethod;
            if (deleteMessageResponse.getMessageId() != null) {
                execute(deleteMessageResponse);
                LOGGER.info("Message {} was successfully deleted", deleteMessageResponse.getMessageId());
            }
        } catch (TelegramApiException e) {
            LOGGER.error("Failed to send message due to error: {}", e.getMessage());
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
