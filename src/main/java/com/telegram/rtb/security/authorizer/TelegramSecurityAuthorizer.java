package com.telegram.rtb.security.authorizer;

import com.telegram.rtb.security.authorizer.builder.CommandTelegramSecurity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Telegram security authorizer with.
 *
 * @author Valentyn Korniienko
 */
@Slf4j
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
