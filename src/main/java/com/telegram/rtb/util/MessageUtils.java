package com.telegram.rtb.util;


import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Message utils.
 */
public final class MessageUtils {

    private MessageUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * Trim message text.
     *
     * @param message     the message
     * @param botUserName the bot user name
     * @return clear command text
     */
    public static String trimMessageText(String message, String botUserName) {
        String substringWithBotName = substringCommandWithBotName(message, botUserName);
        String substringWithoutBotName = substringCommandWithoutBotName(message);
        if (StringUtils.isNotEmpty(substringWithBotName)) {
            return substringWithBotName;
        }
        if (StringUtils.isNotEmpty(substringWithoutBotName)) {
            return substringWithoutBotName;
        }
        return message;
    }

    private static String substringCommandWithBotName(String message, String botUserName) {
        int indexOfBotName = message.indexOf("@" + botUserName);
        if (indexOfBotName != -1) {
            return message.substring(0, indexOfBotName);
        }
        return null;
    }

    private static String substringCommandWithoutBotName(String message) {
        int indexOfSpace = message.indexOf(' ');
        if (message.startsWith("!") && indexOfSpace != -1) {
            return message.substring(0, indexOfSpace).trim();
        }
        return null;
    }

    /**
     * Trim command text.
     *
     * @param message the message
     * @return clear message
     */
    public static String trimCommandText(String message) {
        return message.substring(message.indexOf(' ')).trim();
    }
}
