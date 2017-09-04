package com.example.demo.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Created by OTARANOVSKYI on 25.07.2017.
 */
@Component
public class OuterServiceClient {

    private RestTemplate restTemplate = new RestTemplate();
    private final String relativeUrl = "/simpleServer/request";

    @Autowired
    private HostHolder hostHolder;

    public String getResponseFromOuterService() {

        try {
            URI url = URI.create(hostHolder.getHost() + relativeUrl);
            System.out.println(url);
            ResponseEntity forEntity = restTemplate.getForEntity(url, String.class);

            return (String) forEntity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return "an error occurred";
        }
    }
}
