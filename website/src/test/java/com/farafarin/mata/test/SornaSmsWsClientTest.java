package com.farafarin.mata.test;

import com.farafarin.mata.common.model.SmsModel;
import com.farafarin.mata.common.service.SmsService;
import com.farafarin.mata.test.config.DaoConfig;
import com.farafarin.mata.test.config.RootConfig;
import com.farafarin.mata.test.config.SoapClientConfig;
import com.farafarin.mata.web.config.ThreadConfig;
import com.mata.webservice.sms.client.SornaSmsClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by K550 VX on 12/7/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SoapClientConfig.class, RootConfig.class ,ThreadConfig.class})
public class SornaSmsWsClientTest {

//    @Autowired
//    private SornaSmsClient sornaSmsClient;

    private final static Logger log = LoggerFactory.getLogger(SornaSmsWsClientTest.class);

    @Autowired
    private SmsService smsService;

    @Test
    public void sendSingleSms() {
        SmsModel smsModel = new SmsModel();
        smsModel.setDestinationNumber("09389323344");
        smsModel.setContent("tada");
        smsService.afeSendSms(smsModel);
        log.info("test is done");
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        sornaSmsClient.sendSingleSms(smsModel);
    }

}
