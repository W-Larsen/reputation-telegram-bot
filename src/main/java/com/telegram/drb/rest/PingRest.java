package com.telegram.drb.rest;

import com.telegram.drb.model.rest.ping.PingResponse;
import com.telegram.drb.service.ping.impl.PingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Ping rest controller.
 *
 * @author Valentyn Korniienko
 */
@RestController
public class PingRest {

    @Autowired
    private PingService pingService;

    /**
     * Ping.
     *
     * @return ping response
     */
    @GetMapping(value = {"/", "/ping"})
    public PingResponse ping() {
        return pingService.ping();
    }
}
