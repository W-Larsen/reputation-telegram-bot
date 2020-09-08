package com.telegram.rtb.util;

import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * Message utils.
 */
public final class MessageUtils {

    private MessageUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * Trim bot user name.
     *
     * @param commandText the command text
     * @param botUserName the bot user name
     * @return clear command text
     */
    public static String trimBotUserName(String commandText, String botUserName) {
        return commandText.replaceAll("@" + botUserName, EMPTY);
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
