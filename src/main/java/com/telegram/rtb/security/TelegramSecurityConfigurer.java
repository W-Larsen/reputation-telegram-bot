package com.telegram.rtb.security;

import com.telegram.rtb.security.authorizer.TelegramSecurityAuthorizer;
import com.telegram.rtb.security.authorizer.builder.AbstractSecurityBuilder;

/**
 * Telegram security configurer.
 * Should be used when configuration the application by implementing this interface.
 *
 * @author Valentyn Korniienko
 */
public interface TelegramSecurityConfigurer {

    /**
     * Main security configuration. Need to add '@Bean' annotation during configuration
     *
     * @param telegramSecurityAuthorizer the telegram security authorizer
     * @return accumulated security entity for specified type
     */
    AbstractSecurityBuilder configure(TelegramSecurityAuthorizer telegramSecurityAuthorizer);
}
