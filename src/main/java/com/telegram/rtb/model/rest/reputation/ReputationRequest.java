package com.telegram.rtb.model.rest.reputation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Reputation request.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReputationRequest {

    private Long userId;
    private Long chatId;
    private Integer reputation;

}
