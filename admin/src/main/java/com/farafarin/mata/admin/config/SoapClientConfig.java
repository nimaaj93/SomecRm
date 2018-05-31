package com.farafarin.mata.admin.config;

import com.mata.webservice.afe.sms.client.AfeSmsClient;
import com.mata.webservice.sms.client.SornaSmsClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * Created by K550 VX on 12/20/2017.
 */
@Configuration
public class SoapClientConfig {

//    @Bean
//    public Jaxb2Marshaller marshaller() {
//        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//        marshaller.setContextPath("com.mata.webservice.sms");
//        return marshaller;
//    }
//
//    @Bean
//    public SornaSmsClient sornaSmsClient(Jaxb2Marshaller marshaller) {
//        SornaSmsClient client = new SornaSmsClient();
//        client.setDefaultUri("http://www.sornasms.net/webpublish2/sornaservice.asmx");
//        client.setMarshaller(marshaller);
//        client.setUnmarshaller(marshaller);
//        return client;
//    }


    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.mata.webservice.afe.sms");
        return marshaller;
    }

    @Bean
    public AfeSmsClient afeSmsClient(Jaxb2Marshaller marshaller) {
        AfeSmsClient client = new AfeSmsClient();
        client.setDefaultUri("http://www.afe.ir/WebService/V4/BoxService.asmx");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
