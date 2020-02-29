package com.telegram.drb.service.ping.impl;

import com.telegram.drb.model.rest.ping.PingResponse;
import com.telegram.drb.service.ping.IPingService;
import org.springframework.stereotype.Service;

import static com.telegram.drb.model.rest.ping.PingStatus.AVAILABLE;

/**
 * Ping implementation.
 *
 * @author Valentyn Korniienko
 */
@Service
public class PingService implements IPingService {

    @Override
    public PingResponse ping() {
        return createPingResponse();
    }

    private PingResponse createPingResponse() {
        PingResponse response = new PingResponse();
        response.setStatus(AVAILABLE);
        response.setMessage("Service is available");
        return response;
    }
}
