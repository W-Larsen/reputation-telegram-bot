package com.telegram.drb.model.message;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import java.util.List;

/**
 * Bot api message response.
 */
public class BotApiMethodResponse {

    private List<BotApiMethod<?>> botApiMethods;
    private MethodName methodName;

    /**
     * Gets bot api methods.
     *
     * @return bot api methods
     */
    public List<BotApiMethod<?>> getBotApiMethods() {
        return botApiMethods;
    }

    /**
     * Sets bot api methods.
     *
     * @param botApiMethods the bot api methods
     */
    public void setBotApiMethods(List<BotApiMethod<?>> botApiMethods) {
        this.botApiMethods = botApiMethods;
    }

    /**
     * Gets method name.
     *
     * @return method name
     */
    public MethodName getMethodName() {
        return methodName;
    }

    /**
     * Sets method name.
     *
     * @param methodName the method name
     */
    public void setMethodName(MethodName methodName) {
        this.methodName = methodName;
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
        BotApiMethodResponse rhs = (BotApiMethodResponse) obj;
        return new EqualsBuilder()
                .append(this.botApiMethods, rhs.botApiMethods)
                .append(this.methodName, rhs.methodName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(botApiMethods)
                .append(methodName)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("botApiMethods", botApiMethods)
                .append("methodName", methodName)
                .toString();
    }

}
