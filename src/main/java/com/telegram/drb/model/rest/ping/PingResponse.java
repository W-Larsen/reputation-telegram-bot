package com.telegram.drb.model.rest.ping;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Ping response type.
 */
public class PingResponse {

    private PingStatus status;
    private String message;

    /**
     * Gets status.
     *
     * @return status
     */
    public PingStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(PingStatus status) {
        this.status = status;
    }

    /**
     * Gets message.
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        PingResponse rhs = (PingResponse) obj;
        return new EqualsBuilder()
            .append(this.status, rhs.status)
            .append(this.message, rhs.message)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(status)
            .append(message)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("status", status)
            .append("message", message)
            .toString();
    }

}
