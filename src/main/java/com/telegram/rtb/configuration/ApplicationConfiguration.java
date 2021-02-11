package com.telegram.rtb.configuration;

import com.telegram.rtb.model.message.ChatAdministrators;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

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
    public Map<Long, Queue<Message>> messageCache() {
        return new HashMap<>();
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
}
