package com.telegram.rtb.model.rest.reputation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Reputation request.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReputationRequest {

    private Integer userId;
    private Long chatId;
    private Integer reputation;

}
