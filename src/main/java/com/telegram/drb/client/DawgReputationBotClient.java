package com.telegram.drb.client;

import com.telegram.drb.model.rest.ping.PingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Dawg reputation bot client.
 *
 * @author Valentyn Korniienko
 */
@Component
public class DawgReputationBotClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${dawg.ping.url}")
    private String url;

    /**
     * Ping.
     *
     * @return ping response
     */
    public PingResponse ping() {
        ResponseEntity<PingResponse> response = restTemplate.getForEntity(url + "/ping", PingResponse.class);
        return response.getBody();
    }

}
