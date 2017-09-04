package com.example.demo.client;

import org.springframework.stereotype.Component;

/**
 * Created by OTARANOVSKYI on 25.07.2017.
 */
@Component
public class HostHolder {

    public static final String DEFAULT_HOST = "http://localhost:18080";

    private String host;

    public String getHost() {
        return host == null ? DEFAULT_HOST : host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
