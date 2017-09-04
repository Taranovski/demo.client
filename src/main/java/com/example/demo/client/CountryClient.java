package com.example.demo.client;

/**
 * Created by OTARANOVSKYI on 27.07.2017.
 */
import country.wsdl.GetCountryRequest;
import country.wsdl.GetCountryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class CountryClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(CountryClient.class);

    @Autowired
    private HostHolder hostHolder;

    public GetCountryResponse getCountry(String countryName) {

        GetCountryRequest request = new GetCountryRequest();
        request.setName(countryName);

        log.info("Requesting quote for " + countryName);

        GetCountryResponse response = (GetCountryResponse) getWebServiceTemplate()
                .marshalSendAndReceive(hostHolder.getHost() + "/simpleServer/ws",
                        request,
                        new SoapActionCallback("http://spring.io/guides/gs-producing-web-service/getCountryRequest"));

        return response;
    }

}
