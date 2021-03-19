package com.telegram.rtb.security;

import com.telegram.rtb.security.builder.TelegramSecurity;

public abstract class TelegramSecurityConfigurer {

    protected abstract void configure(TelegramSecurity telegramSecurity);

}
