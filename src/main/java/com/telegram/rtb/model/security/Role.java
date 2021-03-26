package com.telegram.rtb.model.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum Role {

    CREATOR("creator"),

    ADMIN("administrator"),

    MEMBER("member");

    private String value;

    public static String[] getRoleNames() {
        return Arrays.stream(Role.values()).map(Enum::name).toArray(String[]::new);
    }

}
