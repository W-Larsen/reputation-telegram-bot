package com.telegram.drb.service.ping;

import com.telegram.drb.model.rest.ping.PingResponse;

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
