package com.farafarin.mata.web.front.controller;

import com.farafarin.mata.common.dto.OfferDto;
import com.farafarin.mata.common.dto.UserMessageDto;
import com.farafarin.mata.web.back.service.UserOfferAndMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Asus on 11/25/2017.
 */

@RestController
@RequestMapping(value = "/s/promotion")
public class UserOfferAndMessageController {

    @Autowired
    private UserOfferAndMessageService userOfferAndMessageService;

    @RequestMapping(value = "/newmessagecount",method = RequestMethod.GET)
    public Long getUserNewMessageCount(){
        return userOfferAndMessageService.getUserNewMessageCount();
    }

    @RequestMapping(value = "/messages",method = RequestMethod.GET)
    public List<UserMessageDto> getUserNewMessages(){
        return userOfferAndMessageService.getUserMessages();
    }

    @RequestMapping(value = "/updatemessage",method = RequestMethod.POST)
    public void updateUserMessageStatus(){
        userOfferAndMessageService.updateUserMessagesStatus();
    }

    @RequestMapping(value = "/seenoffers",method = RequestMethod.POST)
    public void submitUserSeenOffers(){
        userOfferAndMessageService.submitUserSeenOffers();
    }

    @RequestMapping(value = "/newoffercount",method = RequestMethod.GET)
    public Long getUserNewOfferCount(){
        return userOfferAndMessageService.getUserNewOfferCount();
    }

    @RequestMapping(value = "/newofferlist",method = RequestMethod.GET)
    public List<OfferDto> getNewOffers(){
        return userOfferAndMessageService.getOffers();
    }
}
