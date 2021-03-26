package com.telegram.rtb.configuration.security;

import com.telegram.rtb.security.authorizer.TelegramSecurityAuthorizer;
import com.telegram.rtb.security.TelegramSecurityConfigurer;
import com.telegram.rtb.security.authorizer.builder.CommandTelegramSecurity;
import org.springframework.context.annotation.Configuration;

import static com.telegram.rtb.model.security.Role.ADMIN;
import static com.telegram.rtb.model.security.Role.CREATOR;
import static com.telegram.rtb.util.UserRoleUtils.getAllRoles;
import static com.telegram.rtb.util.UserRoleUtils.getUserRole;

@Configuration
public class CommandTelegramSecurityConfiguration implements TelegramSecurityConfigurer {

    @Override
    public CommandTelegramSecurity configure(TelegramSecurityAuthorizer telegramSecurityAuthorizer) {
        return telegramSecurityAuthorizer.authorizeCommands()
                .byDefault(getAllRoles())
                .matchers("!add_minus_keywords").hasAnyRoles(getUserRole(CREATOR), getUserRole(ADMIN))
                .matchers("!add_plus_keywords").hasAnyRoles(getUserRole(CREATOR), getUserRole(ADMIN))
                .apply();

    }
}
