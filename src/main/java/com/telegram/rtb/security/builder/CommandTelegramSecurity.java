package com.telegram.rtb.security.builder;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

@Log4j2
@Component
public class CommandTelegramSecurity extends AbstractSecurityBuilder {

    private static final String DEFAULT_COMMANDS = "default_command_roles";

    private final Map<String, List<String>> commandPermissions = new HashMap<>();

    private List<String> defaultCommandRoles = new ArrayList<>();
    private final Queue<String> commands = new ArrayDeque<>();
    private final Queue<List<String>> roles = new ArrayDeque<>();

    public CommandTelegramSecurity byDefault(String... telegramRoles) {
        defaultCommandRoles = Arrays.asList(telegramRoles);
        return this;
    }

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

    @Override
    public CommandTelegramSecurity apply() {
        checkCommandConfiguration();

        while (!commands.isEmpty() && !roles.isEmpty()) {
            commandPermissions.put(DEFAULT_COMMANDS, defaultCommandRoles);
            commandPermissions.put(commands.poll(), roles.poll());
        }
        return this;
    }

    @Override
    public Map<String, List<String>> getCommandPermissions() {
        return commandPermissions;
    }

    private void checkCommandConfiguration() {
        if (commands.size() != roles.size()) {
            String errMess = "Wrong telegram security configuration. #matchers() method and #withPermissions() method should be one after another.";
            log.error(errMess);
            throw new BeanCreationException(errMess);
        }
    }
}
