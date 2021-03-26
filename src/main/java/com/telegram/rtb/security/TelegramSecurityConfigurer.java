package com.telegram.rtb.security;

import com.telegram.rtb.security.authorizer.TelegramSecurityAuthorizer;
import com.telegram.rtb.security.authorizer.builder.AbstractSecurityBuilder;
import org.springframework.context.annotation.Bean;

public interface TelegramSecurityConfigurer {

    @Bean
    AbstractSecurityBuilder configure(TelegramSecurityAuthorizer telegramSecurityAuthorizer);

}
