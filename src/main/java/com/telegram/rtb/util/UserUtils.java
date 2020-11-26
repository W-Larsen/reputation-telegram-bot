package com.telegram.rtb.util;

import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.meta.api.objects.User;

/**
 * Users utils.
 */
public final class UserUtils {

    private UserUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * Gets full user name.
     *
     * @param user the user
     * @return full name
     */
    public static String getFullName(User user) {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        if (StringUtils.isNotEmpty(lastName)) {
            return firstName + " " + lastName;
        }
        return firstName;
    }

}
