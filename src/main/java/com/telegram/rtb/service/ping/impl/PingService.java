package com.telegram.rtb.service.ping.impl;

import com.telegram.rtb.model.rest.ping.PingResponse;
import com.telegram.rtb.service.ping.IPingService;
import org.springframework.stereotype.Service;

import static com.telegram.rtb.model.rest.ping.PingStatus.AVAILABLE;

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
        return PingResponse.builder()
                .status(AVAILABLE)
                .message("Service is available")
                .build();
    }
}
