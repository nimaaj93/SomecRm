package com.farafarin.mata.admin.back.service.impl;

import com.farafarin.mata.common.dao.OfferDao;
import com.farafarin.mata.common.dto.OfferDto;
import com.farafarin.mata.common.entity.OfferEntity;
import com.farafarin.mata.common.model.OfferUpdateModel;
import com.farafarin.mata.admin.back.model.UserOfferModel;
import com.farafarin.mata.common.searchobject.OfferSO;
import com.farafarin.mata.admin.back.service.OfferManagementService;
import com.farafarin.mata.common.constant.OfferStatus;
import com.farafarin.mata.common.security.model.UserProfile;
import com.farafarin.mata.common.util.AuthenticationUtil;
import com.farafarin.mata.common.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by Asus on 11/21/2017.
 */
@Component
public class OfferManagementServiceImpl implements OfferManagementService {

    @Autowired
    private OfferDao offerDao;
    @Override
    public void insertUserOffer(UserOfferModel userOfferModel) {
        OfferEntity offerEntity = prepareUserOfferEntity(userOfferModel);
        BeanUtils.copyProperties(userOfferModel, offerEntity);
        offerDao.insertUserOffer(offerEntity);
    }

    @Override
    public List<OfferDto> getOffers(OfferSO offerSO) {
        return offerDao.getOffers(offerSO);
    }

    @Override
    public void updateOffer(OfferUpdateModel offerUpdateModel) {
        offerDao.updateOffer(offerUpdateModel);
    }

    @Override
    public Long getOfferCount(OfferSO offerSO) {
        return offerDao.getOfferCount(offerSO);
    }

    @Override
    public Long getActiveOffersCount() {
        return offerDao.getActiveOfferCount(OfferStatus.NEW.getOfferStatus());
    }

    private OfferEntity prepareUserOfferEntity(UserOfferModel userOfferModel){
        OfferEntity offerEntity = new OfferEntity();
        Date offerExpireDateInGregorian =
                DateUtil.convertStringJalahiDateToGregorianDate(userOfferModel.getOfferExpireDate());
        offerEntity.setOfferExpireDate(offerExpireDateInGregorian);
        UserProfile currentUser = AuthenticationUtil.getCurrentUser();
        offerEntity.setSenderUserId(currentUser.getUserId());
        offerEntity.setOfferStatus(OfferStatus.NEW.getOfferStatus());
        return offerEntity;
    }
}
