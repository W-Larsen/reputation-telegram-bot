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
        return message.substring(0, message.indexOf("@" + botUserName));
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

    /**
     * Gets possible managing command from related messages map.
     *
     * @param commandText     the command text
     * @param relatedMessages the related messages
     * @return possible command
     */
    public static String getPossibleManagingCommand(String commandText, Map<String, List<String>> relatedMessages) {
        return relatedMessages.entrySet().stream()
                .filter(entry -> entry.getValue().stream().anyMatch(commandText::equalsIgnoreCase))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(commandText);
    }
}
