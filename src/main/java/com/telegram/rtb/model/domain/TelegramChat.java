package com.telegram.rtb.model.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Telegram chat model.
 */
public class TelegramChat {

    private Long chatId;
    private String chatName;

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
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
        TelegramChat rhs = (TelegramChat) obj;
        return new EqualsBuilder()
                .append(this.chatId, rhs.chatId)
                .append(this.chatName, rhs.chatName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(chatId)
                .append(chatName)
                .toHashCode();
    }

}
