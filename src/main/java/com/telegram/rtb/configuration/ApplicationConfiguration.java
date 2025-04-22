package com.telegram.rtb.configuration;

import com.telegram.rtb.model.cache.MessageCache;
import com.telegram.rtb.model.message.ChatAdministrators;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.HashMap;

/**
 * Database configuration.
 *
 * @author Valentyn Korniienko
 */
@Configuration
@ComponentScan("com.telegram.rtb")
@PropertySource(value = {"classpath:application.properties"})
@EnableScheduling
public class ApplicationConfiguration {

    /**
     * RestTemplate bean.
     *
     * @return rest template
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * Message cache by chat id.
     *
     * @return cache with messages by chat id
     */
    @Bean
    public MessageCache messageCache() {
        return MessageCache.builder()
                .messageCache(new HashMap<>())
                .build();
    }


    /**
     * ChatAdministrators entity with map of chat administrators by chat id.
     * By default, it initialized empty. Will be populated at runtime.
     *
     * @return chat administrators
     */
    @Bean
    public ChatAdministrators chatAdministratorsByChatId() {
        return ChatAdministrators.builder()
                .chatAdministratorsByChatId(new HashMap<>())
                .build();
    }

    @Bean
    public TelegramClient telegramClient(@Value("${telegram.reputation.bot.token}") String botToken) {
        return new OkHttpTelegramClient(botToken);
    }
}
