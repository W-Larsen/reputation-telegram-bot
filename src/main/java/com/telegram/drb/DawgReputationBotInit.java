package com.telegram.drb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

/**
 * Dawg reputation bot initializer.
 *
 * @author Valentyn Korniienko
 */
@SpringBootApplication
public class DawgReputationBotInit {

    /**
     * Main class.
     *
     * @param args the args
     */
    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(DawgReputationBotInit.class, args);
    }

}
