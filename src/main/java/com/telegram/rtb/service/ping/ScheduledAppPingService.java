package com.telegram.rtb.service.ping;

import com.telegram.rtb.bot.TelegramReputationBot;
import com.telegram.rtb.client.TelegramReputationBotClient;
import com.telegram.rtb.model.rest.ping.PingResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Service to scheduled application ping.
 *
 * This is necessary to prevent bot sleep.
 *
 * @author Valentyn Korniienko
 */
@Service
public class ScheduledAppPingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramReputationBot.class);

    @Autowired
    private TelegramReputationBotClient reputationBotClient;

    /**
     * Scheduled method to ping application.
     */
    @Scheduled(cron = "${telegram.ping.scheduled.cron}")
    public void pingBot() {
        PingResponse ping = reputationBotClient.ping();
        LOGGER.info("Application status is '{}'. Message: '{}'", ping.getStatus(), ping.getMessage());
    }
}
