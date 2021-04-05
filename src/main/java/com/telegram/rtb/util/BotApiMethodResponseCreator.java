package com.telegram.rtb.util;

import com.telegram.rtb.model.message.BotApiMethodResponse;

import java.util.Collections;

import static com.telegram.rtb.model.message.MethodName.NO_METHOD;

/**
 * Bot api method response creator.
 */
public final class BotApiMethodResponseCreator {

    private BotApiMethodResponseCreator() {
        throw new UnsupportedOperationException();
    }

    /**
     * Create default BotApiMethodResponse.
     *
     * @return BotApiMethodResponse entity
     */
    public static BotApiMethodResponse createDefaultBotApiMethodResponse() {
        return BotApiMethodResponse.builder()
                .botApiMethods(Collections.emptyList())
                .methodName(NO_METHOD)
                .build();
    }
}
