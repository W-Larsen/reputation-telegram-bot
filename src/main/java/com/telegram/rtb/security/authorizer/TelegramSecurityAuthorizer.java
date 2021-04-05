package com.telegram.rtb.security.authorizer;

import com.telegram.rtb.security.authorizer.builder.CommandTelegramSecurity;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * Telegram security authorizer with.
 *
 * @author Valentyn Korniienko
 */
@Log4j2
@Component
public class TelegramSecurityAuthorizer {

    /**
     * Starting point for configuration commands.
     *
     * @return empty CommandTelegramSecurity entity
     */
    public CommandTelegramSecurity authorizeCommands() {
        return new CommandTelegramSecurity();
    }
}
