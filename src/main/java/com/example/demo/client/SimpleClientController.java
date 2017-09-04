package com.example.demo.client;

import country.wsdl.Country;
import country.wsdl.Currency;
import country.wsdl.GetCountryRequest;
import country.wsdl.GetCountryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

/**
 * Created by OTARANOVSKYI on 25.07.2017.
 */
@RestController
public class SimpleClientController {

    @Autowired
    private OuterServiceClient outerServiceClient;

    @Autowired
    private HostHolder hostHolder;
    @Autowired
    private CountryClient countryClient;

    @RequestMapping(method = RequestMethod.GET, path = "/getFromTheDeep")
    public String getResponseFromOuterService() {
        return outerServiceClient.getResponseFromOuterService();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getCountry", produces = "application/xml")
    public String getResponseFromSoapOuterService(@RequestParam("country") String countryName) throws JAXBException {
        GetCountryResponse getCountryResponse = countryClient.getCountry(countryName);

        JAXBContext jaxbContext = JAXBContext.newInstance(Country.class,
                Currency.class,
                GetCountryRequest.class,
                GetCountryResponse.class);

        Marshaller marshaller = jaxbContext.createMarshaller();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        marshaller.marshal(getCountryResponse, byteArrayOutputStream);

        return new String(byteArrayOutputStream.toByteArray(), Charset.forName("UTF8"));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/changeHost")
    public void changeHost(@RequestParam("newHost") String newHost) {
        hostHolder.setHost(newHost);
    }
}
