package com.telegram.rtb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Telegram reputation bot initializer.
 *
 * @author Valentyn Korniienko
 */
@SpringBootApplication
@EnableJpaRepositories
public class TelegramReputationBotInit {

    /**
     * Main class.
     *
     * @param args the args
     */
    public static void main(String[] args) {
        SpringApplication.run(TelegramReputationBotInit.class, args);
    }

}
