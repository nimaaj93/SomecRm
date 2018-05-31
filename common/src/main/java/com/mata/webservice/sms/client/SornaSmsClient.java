package com.mata.webservice.sms.client;

import com.farafarin.mata.common.exception.UnknownSystemException;
import com.farafarin.mata.common.logback.annotation.Log;
import com.farafarin.mata.common.model.SmsModel;
import com.mata.webservice.sms.SingleSMSEngine;
import com.mata.webservice.sms.SingleSMSEngine021Response;
import com.mata.webservice.sms.SingleSMSEngineResponse;
import org.slf4j.Logger;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

/**
 * Created by K550 VX on 12/7/2017.
 */
public class SornaSmsClient extends WebServiceGatewaySupport {

    private @Log Logger log;

    public void sendSingleSms(SmsModel smsModel) {
        try {
            SingleSMSEngine request = new SingleSMSEngine();
            request.setPortalCode(8816);
            request.setUserName("msgadmin10332");
            request.setPassWord("10249");
            request.setMobile(smsModel.getDestinationNumber());
            request.setMessage(smsModel.getContent());

            SingleSMSEngineResponse response = (SingleSMSEngineResponse) getWebServiceTemplate().
                    marshalSendAndReceive("http://www.sornasms.net/webpublish2/sornaservice.asmx",
                            request,
                            new SoapActionCallback("http://tempuri.org/SingleSMSEngine"));
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new UnknownSystemException(e);
        }
    }

}
