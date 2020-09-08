package com.telegram.rtb.model.rest.ping;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Ping response type.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PingResponse {

    private PingStatus status;
    private String message;

}
