package com.telegram.rtb.model.rest.reputation;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Reputation request.
 */
public class ReputationRequest {

    private Integer userId;
    private Long chatId;
    private Integer reputation;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Integer getReputation() {
        return reputation;
    }

    public void setReputation(Integer reputation) {
        this.reputation = reputation;
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
        ReputationRequest rhs = (ReputationRequest) obj;
        return new EqualsBuilder()
                .append(this.userId, rhs.userId)
                .append(this.chatId, rhs.chatId)
                .append(this.reputation, rhs.reputation)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(userId)
                .append(chatId)
                .append(reputation)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("chatId", chatId)
                .append("reputation", reputation)
                .toString();
    }

}
