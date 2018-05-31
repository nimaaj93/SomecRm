package com.farafarin.mata.web.back.service;

import com.farafarin.mata.common.dto.OfferDto;
import com.farafarin.mata.common.dto.UserMessageDto;

import java.util.List;

/**
 * Created by Asus on 11/25/2017.
 */
public interface UserOfferAndMessageService {

    Long getUserNewMessageCount();

    List<UserMessageDto> getUserMessages();

    void updateUserMessagesStatus();

    void submitUserSeenOffers();

    Long getUserNewOfferCount();

    List<OfferDto> getOffers();


}
