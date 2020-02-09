package com.telegram.drb.command.handler;

import com.telegram.drb.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Map;

/**
 * Command handler.
 *
 * @author Valenyn Korniienko
 */
@Component
public class CommandHandler {

    @Autowired
    private Map<String, Command> commandMap;

    public void handleMessage(Message message) {

    }
}
