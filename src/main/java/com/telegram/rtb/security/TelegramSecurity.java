package com.telegram.rtb.security;

import com.telegram.rtb.security.builder.CommandTelegramSecurity;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class TelegramSecurity {

    public CommandTelegramSecurity authorizeCommands() {
        return new CommandTelegramSecurity();
    }
}
