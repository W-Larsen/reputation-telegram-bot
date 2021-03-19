package com.telegram.rtb.security.builder;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

@Component
@Log4j2
public class TelegramSecurity {

    public CommandTelegramSecurity authorizeCommands() {
        return new CommandTelegramSecurity();
    }

    public static class CommandTelegramSecurity extends AbstractSecurityBuilder {

        private final Map<String, List<String>> commandPermissions = new HashMap<>();

        private final Queue<String> commands = new ArrayDeque<>();
        private final Queue<List<String>> roles = new ArrayDeque<>();

        public CommandTelegramSecurity matchers(String command) {
            commands.add(command);
            return this;
        }

        public CommandTelegramSecurity hasRole(String role) {
            roles.add(List.of(role));
            return this;
        }

        public CommandTelegramSecurity hasAnyRoles(String... telegramRoles) {
            roles.add(List.of(telegramRoles));
            return this;
        }

        public void apply() {
            checkCommandConfiguration();

            while (!commands.isEmpty() && !roles.isEmpty()) {
                commandPermissions.put(commands.poll(), roles.poll());
            }
        }

        private void checkCommandConfiguration() {
            if (commands.size() != roles.size()) {
                String errMess = "Wrong telegram security configuration. #matchers() method and #withPermissions() method should be one after another.";
                log.error(errMess);
                throw new BeanCreationException(errMess);
            }
        }

    }
}
