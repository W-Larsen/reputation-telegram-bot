package com.telegram.rtb.configuration.security;

import com.telegram.rtb.security.TelegramSecurityConfigurer;
import com.telegram.rtb.security.authorizer.TelegramSecurityAuthorizer;
import com.telegram.rtb.security.authorizer.builder.CommandTelegramSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.telegram.rtb.model.security.Role.ADMIN;
import static com.telegram.rtb.model.security.Role.CREATOR;
import static com.telegram.rtb.util.UserRoleUtils.getUserRole;

/**
 * Command telegram security configuration for application.
 *
 * @author Valentyn Korniienko
 */
@Configuration
public class CommandTelegramSecurityConfiguration implements TelegramSecurityConfigurer {

    @Bean
    @Override
    public CommandTelegramSecurity configure(TelegramSecurityAuthorizer telegramSecurityAuthorizer) {
        return telegramSecurityAuthorizer.authorizeCommands()
                .matchers("!add_plus_keywords").hasAnyRoles(getUserRole(CREATOR), getUserRole(ADMIN))
                .matchers("!add_minus_keywords").hasAnyRoles(getUserRole(CREATOR), getUserRole(ADMIN))
                .matchers("!del_plus_keyword").hasAnyRoles(getUserRole(CREATOR), getUserRole(ADMIN))
                .matchers("!del_minus_keyword").hasAnyRoles(getUserRole(CREATOR), getUserRole(ADMIN))
                .matchers("!del_all_plus_keywords").hasAnyRoles(getUserRole(CREATOR), getUserRole(ADMIN))
                .matchers("!del_all_minus_keywords").hasAnyRoles(getUserRole(CREATOR), getUserRole(ADMIN))
                .allowAllCommandsByDefault(true)
                .apply();

    }
}
