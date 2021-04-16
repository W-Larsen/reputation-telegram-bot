package com.telegram.rtb.security.authorizer.builder;

/**
 * Abstract class for security configuration.
 *
 * @author Valentyn Korniienko
 */
public abstract class AbstractSecurityBuilder {

    /**
     * Terminal operation that has each implementation of security type. Collect all data together.
     *
     * @return accumulated security entity
     */
    public abstract AbstractSecurityBuilder apply();
}
