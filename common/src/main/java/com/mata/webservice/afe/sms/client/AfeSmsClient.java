package com.mata.webservice.afe.sms.client;

import com.farafarin.mata.common.logback.annotation.Log;
import com.farafarin.mata.common.model.SmsModel;
import com.farafarin.mata.common.util.Empty;
import com.mata.webservice.afe.sms.ArrayOfString;
import com.mata.webservice.afe.sms.SendMessage;
import com.mata.webservice.afe.sms.SendMessageResponse;
import org.slf4j.Logger;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.swing.plaf.PanelUI;

/**
 * Created by Asus on 5/21/2018.
 */
public class AfeSmsClient extends WebServiceGatewaySupport {
    private @Log Logger log;

    public void sendMessage(SmsModel smsModel){
        SendMessage request = new SendMessage();
        request.setUsername("info@farafarin.com");
        request.setPassword("123!@#aA");
        request.setNumber("30007957956048");
        request.setMessage(smsModel.getContent());
        ArrayOfString arrayOfString = new ArrayOfString();
        arrayOfString.getString().add(smsModel.getDestinationNumber());
        request.setMobile(arrayOfString);
        request.setType("1");

        SendMessageResponse response = (SendMessageResponse) getWebServiceTemplate().
                marshalSendAndReceive("http://www.afe.ir/WebService/V4/BoxService.asmx",
                        request,
                        new SoapActionCallback("http://www.afe.ir/SendMessage"));
        log.info("sms sent to--->" + smsModel.getDestinationNumber());
        if(Empty.isNotEmpty(response.getSendMessageResult().getString())){

            log.info("send sms result--->" + response.getSendMessageResult().getString().get(0));
        }

    }

}
