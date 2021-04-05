package com.telegram.rtb.model.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

/**
 * Telegram roles.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum Role {

    CREATOR("creator"),

    ADMIN("administrator"),

    MEMBER("member");

    private String value;

    /**
     * Get role names.
     *
     * @return array of role names
     */
    public static String[] getRoleNames() {
        return Arrays.stream(Role.values()).map(Enum::name).toArray(String[]::new);
    }

}
