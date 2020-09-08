package com.telegram.rtb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

/**
 * Telegram reputation bot initializer.
 *
 * @author Valentyn Korniienko
 */
@SpringBootApplication
public class TelegramReputationBotInit {

    /**
     * Main class.
     *
     * @param args the args
     */
    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(TelegramReputationBotInit.class, args);
    }

}
