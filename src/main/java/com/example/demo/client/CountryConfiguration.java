package com.example.demo.client;

/**
 * Created by OTARANOVSKYI on 27.07.2017.
 */

    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.oxm.jaxb.Jaxb2Marshaller;

    @Configuration
public class CountryConfiguration {

        @Bean
        public Jaxb2Marshaller marshaller() {
            Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
            // this package must match the package in the <generatePackage> specified in
            // pom.xml
            marshaller.setContextPath("country.wsdl");
            return marshaller;
        }

        @Bean
        public CountryClient quoteClient(Jaxb2Marshaller marshaller) {
            CountryClient client = new CountryClient();
            client.setDefaultUri("http://localhost:18080/simpleServer/ws");
            client.setMarshaller(marshaller);
            client.setUnmarshaller(marshaller);
            return client;
        }

}
