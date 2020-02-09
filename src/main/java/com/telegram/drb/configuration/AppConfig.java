package com.telegram.drb.configuration;

import com.telegram.drb.command.Command;
import com.telegram.drb.command.IncreaseReputationCommand;
import com.telegram.drb.command.ReduceReputationCommand;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Application configuration.
 *
 * @author Valentyn Korniienko
 */
@Configuration
public class AppConfig {

    /**
     * Get command map bean.
     *
     * @return command map
     */
    @Bean
    public Map<String, Command> getCommandMap() {
        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("+", new IncreaseReputationCommand());
        commandMap.put("-", new ReduceReputationCommand());
        return commandMap;
    }

}
