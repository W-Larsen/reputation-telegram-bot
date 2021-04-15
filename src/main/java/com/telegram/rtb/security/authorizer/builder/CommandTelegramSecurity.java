package com.telegram.rtb.security.authorizer.builder;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.BeanCreationException;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Telegram security configuration for commands.
 *
 * @author Valentyn Korniienko
 */
@Log4j2
public class CommandTelegramSecurity extends AbstractSecurityBuilder {

    private Boolean allowedByDefault = Boolean.FALSE;

    private final Map<String, List<String>> commandPermissions = new HashMap<>();

    private final Queue<String> commands = new ArrayDeque<>();
    private final Queue<List<String>> roles = new ArrayDeque<>();

    /**
     * Set default permissions on all commands for all roles. By default - false.
     *
     * @param isAllowedByDefault the flag true/false
     * @return CommandTelegramSecurity entity
     */
    public CommandTelegramSecurity allowAllCommandsByDefault(Boolean isAllowedByDefault) {
        allowedByDefault = isAllowedByDefault;
        return this;
    }

    /**
     * Set matchers for commands.
     *
     * @param command the command name
     * @return CommandTelegramSecurity entity
     */
    public CommandTelegramSecurity matchers(String command) {
        commands.add(command);
        return this;
    }

    /**
     * Set specific role for specific command.
     *
     * @param role the role
     * @return CommandTelegramSecurity entity
     */
    public CommandTelegramSecurity hasRole(String role) {
        roles.add(List.of(role));
        return this;
    }

    /**
     * Set specific roles for specific command.
     *
     * @param telegramRoles the roles
     * @return CommandTelegramSecurity entity
     */
    public CommandTelegramSecurity hasAnyRoles(String... telegramRoles) {
        roles.add(List.of(telegramRoles));
        return this;
    }

    @Override
    public CommandTelegramSecurity apply() {
        checkCommandConfiguration();

        while (!commands.isEmpty() && !roles.isEmpty()) {
            commandPermissions.put(commands.poll(), roles.poll());
        }
        return this;
    }

    /**
     * Get command permissions.
     *
     * @return map with roles by command name
     */
    public Map<String, List<String>> getCommandPermissions() {
        return commandPermissions;
    }

    /**
     * Checks if commands allowed for all roles by default.
     *
     * @return true/false
     */
    public Boolean isAllowedByDefault() {
        return allowedByDefault;
    }

    private void checkCommandConfiguration() {
        if (commands.size() != roles.size()) {
            String errMess = "Wrong telegram security configuration. #matchers() method and #withPermissions() method should be one after another.";
            log.error(errMess);
            throw new BeanCreationException(errMess);
        }
    }
}
