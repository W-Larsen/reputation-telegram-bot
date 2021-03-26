package com.telegram.rtb.security.authorizer;

import com.telegram.rtb.security.authorizer.builder.CommandTelegramSecurity;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class TelegramSecurityAuthorizer {

    public CommandTelegramSecurity authorizeCommands() {
        return new CommandTelegramSecurity();
    }
}
