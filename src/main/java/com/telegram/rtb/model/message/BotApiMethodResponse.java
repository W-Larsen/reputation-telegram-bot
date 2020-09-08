package com.telegram.rtb.model.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import java.util.List;

/**
 * Bot api message response.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BotApiMethodResponse {

    private List<BotApiMethod<?>> botApiMethods;
    private MethodName methodName;

}
