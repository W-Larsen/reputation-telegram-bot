package com.telegram.rtb.security;

import com.telegram.rtb.security.builder.AbstractSecurityBuilder;
import org.springframework.context.annotation.Bean;

public interface TelegramSecurityConfigurer {

    @Bean
    AbstractSecurityBuilder configure(TelegramSecurity telegramSecurity);

}
