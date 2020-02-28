package com.telegram.drb.util;

import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.meta.api.objects.User;

public final class UserUtils {

    private UserUtils() {
        throw new IllegalStateException();
    }

    public String getFullName(User user) {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        if (StringUtils.isNotEmpty(lastName)) {
            return firstName + " " + lastName;
        }
        return firstName;
    }

}
