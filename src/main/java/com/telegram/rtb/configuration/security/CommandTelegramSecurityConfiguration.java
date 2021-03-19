package com.telegram.rtb.configuration.security;

import com.telegram.rtb.security.TelegramSecurityConfigurer;
import com.telegram.rtb.security.builder.TelegramSecurity;
import org.springframework.context.annotation.Configuration;

import static com.telegram.rtb.model.security.Role.ADMIN;
import static com.telegram.rtb.model.security.Role.CREATOR;
import static com.telegram.rtb.util.UserRoleUtils.getUserRole;

@Configuration
public class CommandTelegramSecurityConfiguration extends TelegramSecurityConfigurer {

    @Override
    protected void configure(TelegramSecurity telegramSecurity) {
        telegramSecurity.authorizeCommands()
                .matchers("!add_minus_keywords").hasAnyRoles(getUserRole(CREATOR), getUserRole(ADMIN))
                .matchers("!add_plus_keywords").hasAnyRoles(getUserRole(CREATOR), getUserRole(ADMIN))
                .apply();

    }
}
