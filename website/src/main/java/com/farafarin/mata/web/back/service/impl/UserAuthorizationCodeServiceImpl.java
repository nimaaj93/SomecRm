package com.farafarin.mata.web.back.service.impl;

import com.farafarin.mata.common.constant.UserNotificationMessageType;
import com.farafarin.mata.common.dao.NotificationMessageTemplateDao;
import com.farafarin.mata.common.dao.UserAuthorizationCodeDao;
import com.farafarin.mata.common.dto.UserAuthorizationCodeDto;
import com.farafarin.mata.common.entity.UserAuthorizationCode;
import com.farafarin.mata.common.exception.UsernameNotFoundException;
import com.farafarin.mata.common.model.SmsModel;
import com.farafarin.mata.common.security.model.UserProfile;
import com.farafarin.mata.common.service.SmsService;
import com.farafarin.mata.common.util.ApplicationConfig;
import com.farafarin.mata.common.util.AuthorizationCodeUtil;
import com.farafarin.mata.common.util.Empty;
import com.farafarin.mata.web.back.service.UserAuthorizationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by K550 VX on 12/4/2017.
 */
@Component
public class UserAuthorizationCodeServiceImpl implements UserAuthorizationCodeService {

    @Autowired
    @Qualifier("mataWebUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private UserAuthorizationCodeDao userAuthorizationCodeDao;

    @Autowired
    private NotificationMessageTemplateDao notificationMessageTemplateDao;

    @Override
    public String createAuthorizationCode(String username) {

        UserProfile userProfile = null;
        try {
            userProfile =
                    (UserProfile) userDetailsService.loadUserByUsername(username);
        } catch (org.springframework.security.core.userdetails.UsernameNotFoundException ex) {
            throw new UsernameNotFoundException();
        }
        if (!Empty.isNotEmpty(userProfile)) {
            throw new UsernameNotFoundException();
        }

        Long userId = userProfile.getUserId();
        int authorizationCodeLength
                = Integer.parseInt(ApplicationConfig.getProperty("authorization.code.length"));
        String authorizationCode = AuthorizationCodeUtil.generateRandomAuthorizationCode(authorizationCodeLength);

        UserAuthorizationCode userAuthorizationCode = new UserAuthorizationCode();
        userAuthorizationCode.setUserId(userId);
        userAuthorizationCode.setAuthorizationCode(authorizationCode);
        userAuthorizationCode.setExpireDate(getExpireDate());
        userAuthorizationCode.setUsed(false);

        userAuthorizationCodeDao.create(userAuthorizationCode);

        String messageText = prepareMessageText(authorizationCode);
        SmsModel smsModel = new SmsModel();
        smsModel.setDestinationNumber(userProfile.getPhoneNumber());
        smsModel.setContent(messageText);
//        smsService.sendSms(smsModel);
        smsService.afeSendSms(smsModel);

        return userProfile.getPhoneNumber().replaceAll("\\b(\\d{4})\\d+(\\d{2})", "$1*****$2");
    }

    private String prepareMessageText(String authorizationCode) {
        String template =
                notificationMessageTemplateDao
                        .retrieveTemplateById(UserNotificationMessageType.AUTHORIZATION_CODE.getId())
                        .getMessageTemplate();
        return template.replaceAll("authorizationCode",authorizationCode);
    }

    private Date getExpireDate() {
        final long ONE_MINUTE_IN_MILLIS=60000;
        Calendar date = Calendar.getInstance();
        long t= date.getTimeInMillis();
        Date afterAddingTenMins=new Date(t + (10 * ONE_MINUTE_IN_MILLIS));
        return afterAddingTenMins;
    }

    @Override
    public UserAuthorizationCodeDto getCreatedAuthorization(String username) {

        UserProfile userProfile =
                (UserProfile) userDetailsService.loadUserByUsername(username);
        if (!Empty.isNotEmpty(userProfile)) {
            throw new UsernameNotFoundException();
        }
        Long userId = userProfile.getUserId();

        return userAuthorizationCodeDao.getLatestGeneratedToken(userId);
    }
}
