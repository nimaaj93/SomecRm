package com.farafarin.mata.web.back.service.impl;

import com.farafarin.mata.common.constant.OfferStatus;
import com.farafarin.mata.common.constant.UserMessageSeenStatus;
import com.farafarin.mata.common.dao.MessageDao;
import com.farafarin.mata.common.dao.OfferDao;
import com.farafarin.mata.common.dao.UserOfferDao;
import com.farafarin.mata.common.dto.OfferDto;
import com.farafarin.mata.common.entity.UserOfferEntity;
import com.farafarin.mata.common.logback.annotation.Log;
import com.farafarin.mata.common.security.model.UserProfile;
import com.farafarin.mata.common.util.AuthenticationUtil;
import com.farafarin.mata.common.dto.UserMessageDto;
import com.farafarin.mata.web.back.service.UserOfferAndMessageService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Asus on 11/25/2017.
 */
@Component
public class UserOfferAndMessageServiceImpl implements UserOfferAndMessageService {

    private @Log Logger LOG;

    @Autowired
    private OfferDao offerDao;
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private UserOfferDao userOfferDao;
    @Override
    public Long getUserNewMessageCount() {
        UserProfile currentUser = AuthenticationUtil.getCurrentUser();
        return messageDao.getUserNewMessageCount(UserMessageSeenStatus.NEW.getSeenStatus(),currentUser.getUserId());
    }

    @Override
    public List<UserMessageDto> getUserMessages() {
        UserProfile currentUser = AuthenticationUtil.getCurrentUser();
        return messageDao.getUserNewMessage(currentUser.getUserId());

    }

    @Override
    public void updateUserMessagesStatus() {
        messageDao.updateUserMessageStatus(UserMessageSeenStatus.NEW.getSeenStatus(),
                UserMessageSeenStatus.SEEN.getSeenStatus(),AuthenticationUtil.getCurrentUser().getUserId());
    }

    @Override
    public void submitUserSeenOffers() {
        UserProfile currentUser = AuthenticationUtil.getCurrentUser();
        Long userId = currentUser.getUserId();

        List<OfferDto> validOffers = offerDao.getOffersByStatus(OfferStatus.NEW.getOfferStatus());

        List<Long> seenOfferIds = userOfferDao.getSeenNotExpiredOfferIdsForUser(userId);

        for (OfferDto offerDto : validOffers) {
             if (!seenOfferIds.contains(offerDto.getOfferId())) {
                 try {
                     UserOfferEntity userOfferEntity = new UserOfferEntity();

                     userOfferEntity.setOfferId(offerDto.getOfferId());
                     userOfferEntity.setUserId(userId);

                     userOfferDao.insertUserOffer(userOfferEntity);
                 } catch (Exception e) {
                     LOG.error("Error while inserting user offer for offerid: " + offerDto.getOfferId() + " and userid: " + userId);
                 }
             }
        }
    }

    @Override
    public Long getUserNewOfferCount() {
        UserProfile currentUser = AuthenticationUtil.getCurrentUser();
        Long uerOfferCount = userOfferDao.getUserOfferByUserId(currentUser.getUserId());
        Long activeOffers = offerDao.getActiveOfferCount(OfferStatus.NEW.getOfferStatus());
        return activeOffers - uerOfferCount;
    }

    @Override
    public List<OfferDto> getOffers() {
        return offerDao.getOffersByStatus(OfferStatus.NEW.getOfferStatus());
    }

    private UserOfferEntity prepareUserOfferEntity(Long offerId,Long userId){
        UserOfferEntity userOfferEntity = new UserOfferEntity();
        userOfferEntity.setOfferId(offerId);
        userOfferEntity.setUserId(userId);
        return userOfferEntity;
    }
}
