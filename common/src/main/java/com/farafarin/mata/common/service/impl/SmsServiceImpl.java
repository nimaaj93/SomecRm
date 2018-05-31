package com.farafarin.mata.common.service.impl;

import com.farafarin.mata.common.logback.annotation.Log;
import com.farafarin.mata.common.model.SmsModel;
import com.farafarin.mata.common.service.SmsService;
import com.mata.webservice.afe.sms.client.AfeSmsClient;
import com.mata.webservice.sms.client.SornaSmsClient;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by K550 VX on 12/7/2017.
 */
@Component
public class SmsServiceImpl implements SmsService {

    private @Log Logger log;

//    @Autowired
//    private SornaSmsClient sornaSmsClient;
    @Autowired
    private AfeSmsClient afeSmsClient;

//    @Override
//    @Async
//    public void sendSms(SmsModel smsModel) {
//        try {
//            sornaSmsClient.sendSingleSms(smsModel);
//        } catch (Exception e) {
//            log.info(e.getMessage(),e);
//        }
//    }

    @Override
    public void afeSendSms(SmsModel smsModel) {
        try {
            afeSmsClient.sendMessage(smsModel);
        }catch (Exception e) {
            log.info(e.getMessage(),e);
        }
    }

}
