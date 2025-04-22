package com.telegram.rtb.security.access;

import com.telegram.rtb.model.message.ChatAdministrators;
import com.telegram.rtb.security.authorizer.builder.CommandTelegramSecurity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.chat.Chat;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;

import java.util.List;
import java.util.Optional;

/**
 * Command telegram security access.
 *
 * @author Valentyn Korniienko
 */
@Component
@Log4j2
public class CommandTelegramSecurityAccess implements TelegramSecurityAccess {

    @Autowired
    private CommandTelegramSecurity commandTelegramSecurity;
    @Autowired
    private ChatAdministrators chatAdministrators;

    @Override
    public boolean isAccessAllowed(String command, Chat chat, User from) {
        List<String> allowedRolesForCommand = commandTelegramSecurity.getCommandPermissions().get(command);
        if (CollectionUtils.isEmpty(allowedRolesForCommand)) {
            return commandTelegramSecurity.isAllowedByDefault();
        }
        Optional<ChatMember> optionalAdministrator = chatAdministrators.getAdministratorByChatIdAndUserId(chat.getId().toString(), from.getId());
        if (optionalAdministrator.isPresent()) {
            ChatMember administrator = optionalAdministrator.get();
            return allowedRolesForCommand.stream().anyMatch(allowedRole -> administrator.getStatus().equals(allowedRole));
        }
        return false;
    }
}
