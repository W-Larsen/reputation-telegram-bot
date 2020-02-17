package com.telegram.drb.service;

import com.telegram.drb.model.rest.PingResponse;

/**
 * Ping interface.
 *
 * @author Valentyn Korniienko
 */
public interface IPingService {

    /**
     * Ping.
     *
     * @return response
     */
    PingResponse ping();
}
