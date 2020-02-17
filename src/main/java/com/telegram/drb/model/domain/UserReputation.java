package com.telegram.drb.model.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User reputation model.
 */
public class UserReputation {

    private Integer userId;
    private Long chatId;
    private Integer reputationValue;

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

    public Integer getReputationValue() {
        return reputationValue;
    }

    public void setReputationValue(Integer reputationValue) {
        this.reputationValue = reputationValue;
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
        UserReputation rhs = (UserReputation) obj;
        return new EqualsBuilder()
                .append(this.userId, rhs.userId)
                .append(this.chatId, rhs.chatId)
                .append(this.reputationValue, rhs.reputationValue)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(userId)
                .append(chatId)
                .append(reputationValue)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("chatId", chatId)
                .append("reputationValue", reputationValue)
                .toString();
    }

}
