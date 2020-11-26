package com.telegram.rtb.service.ping;

import com.telegram.rtb.model.rest.ping.PingResponse;

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
