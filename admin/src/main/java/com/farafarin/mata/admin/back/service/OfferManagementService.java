package com.farafarin.mata.admin.back.service;

import com.farafarin.mata.common.dto.OfferDto;
import com.farafarin.mata.common.model.OfferUpdateModel;
import com.farafarin.mata.admin.back.model.UserOfferModel;
import com.farafarin.mata.common.searchobject.OfferSO;

import java.util.List;

/**
 * Created by Asus on 11/21/2017.
 */
public interface OfferManagementService {
    void insertUserOffer(UserOfferModel userOfferModel);
    List<OfferDto> getOffers(OfferSO offerSO);
    void updateOffer(OfferUpdateModel offerUpdateModel);
    Long getOfferCount(OfferSO offerSO);
    Long getActiveOffersCount();
}
