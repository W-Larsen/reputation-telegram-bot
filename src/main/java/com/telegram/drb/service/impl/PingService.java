package com.telegram.drb.service.impl;

import static com.telegram.drb.model.domain.PingStatus.AVAILABLE;

import com.telegram.drb.model.rest.PingResponse;
import com.telegram.drb.service.IPingService;
import org.springframework.stereotype.Service;

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
