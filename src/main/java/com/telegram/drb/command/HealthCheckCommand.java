package com.telegram.drb.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telegram.drb.client.DawgReputationBotClient;
import com.telegram.drb.model.message.BotApiMethodResponse;
import com.telegram.drb.model.rest.ping.PingResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import static com.telegram.drb.model.domain.ParseMode.MARKDOWN_V2;
import static com.telegram.drb.model.message.MethodName.HEALTH_CHECK;
import static java.util.Collections.singletonList;

/**
 * Command to show health of application.
 *
 * @author Valentyn Korniienko
 */
@Component("/health_check")
public class HealthCheckCommand extends AbstractCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(HealthCheckCommand.class);

    @Autowired
    private DawgReputationBotClient reputationBotClient;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public BotApiMethodResponse execute(Message message) {
        PingResponse ping = reputationBotClient.ping();
        try {
            String pingJsonText = objectMapper.writeValueAsString(ping);
            String responseText = "```java\n" + pingJsonText + "\n```";
            return createBotApiMethodResponse(singletonList(createResponseSendMessage(message, responseText)), HEALTH_CHECK);
        } catch (JsonProcessingException e) {
            String errMes = "Exception during parse json";
            LOGGER.warn(errMes);
            throw new IllegalStateException(errMes);
        }
    }

    private SendMessage createResponseSendMessage(Message message, String responseText) {
        SendMessage messageResponse = createDefaultSendMessageResponse(message.getChatId(), responseText);
        messageResponse.setParseMode(MARKDOWN_V2.getValue());
        return messageResponse;
    }

}
