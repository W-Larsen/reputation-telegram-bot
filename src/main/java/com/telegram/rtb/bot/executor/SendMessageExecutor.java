package com.telegram.rtb.bot.executor;

import com.telegram.rtb.model.message.MethodName;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Queue;
import java.util.function.Function;

import static com.telegram.rtb.model.message.MethodName.MANAGE_REPUTATION;

/**
 * Send message executor.
 *
 * @author Valentyn Korniienko
 */
@Component
@Log4j2
public class SendMessageExecutor implements MessageExecutor {

    @Autowired
    private Queue<Message> messageQueue;

    @Override
    public String getMethodName() {
        return "sendmessage";
    }

    @Override
    public void executeMessage(BotApiMethod<?> botApiMethod, MethodName methodName, Function<BotApiMethod<?>, Message> executorFunction) {
        SendMessage sendMessageResponse = (SendMessage) botApiMethod;
        if (StringUtils.isNotEmpty(sendMessageResponse.getText())) {
            Message message = executorFunction.apply(sendMessageResponse);
            if (methodName.equals(MANAGE_REPUTATION)) {
                messageQueue.add(message);
            }
            log.info("Sent message \"{}\" to {} chat ", sendMessageResponse.getText(), sendMessageResponse.getChatId());
        }
    }
}
