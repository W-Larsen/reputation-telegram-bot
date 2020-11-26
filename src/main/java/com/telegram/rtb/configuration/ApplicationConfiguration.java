package com.telegram.rtb.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayDeque;
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
     * Message queue.
     *
     * @return message queue
     */
    @Bean
    public Queue<Message> messageQueue() {
        return new ArrayDeque<>();
    }

}
