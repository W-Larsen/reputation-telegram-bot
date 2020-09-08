package com.telegram.rtb.model.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * User reputation model.
 */
public class UserReputation {

    private Integer userId;
    private Long chatId;
    private Integer reputationValue;
    private Date updatedDatetime;
    private Integer updatedFrom;

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

    public Date getUpdatedDatetime() {
        return updatedDatetime;
    }

    public void setUpdatedDatetime(Date updatedDatetime) {
        this.updatedDatetime = updatedDatetime;
    }

    public Integer getUpdatedFrom() {
        return updatedFrom;
    }

    public void setUpdatedFrom(Integer updatedFrom) {
        this.updatedFrom = updatedFrom;
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
                .append(this.updatedDatetime, rhs.updatedDatetime)
                .append(this.updatedFrom, rhs.updatedFrom)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(userId)
                .append(chatId)
                .append(reputationValue)
                .append(updatedDatetime)
                .append(updatedFrom)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("chatId", chatId)
                .append("reputationValue", reputationValue)
                .append("updatedDatetime", updatedDatetime)
                .append("updatedFrom", updatedFrom)
                .toString();
    }

}
