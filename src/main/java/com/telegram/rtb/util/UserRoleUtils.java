package com.telegram.rtb.util;

import com.telegram.rtb.model.security.Role;

public final class UserRoleUtils {

    private UserRoleUtils() {
        throw new UnsupportedOperationException();
    }

    public static String getUserRole(Role role) {
        return role.getValue();
    }

    public static String[] getAllRoles() {
        return Role.getRoleNames();
    }
}
