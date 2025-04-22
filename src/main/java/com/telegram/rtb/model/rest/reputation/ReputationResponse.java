package com.telegram.rtb.model.rest.reputation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Reputation response.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReputationResponse {

    private Long userId;
    private Long chatId;
    private Integer reputation;

}
