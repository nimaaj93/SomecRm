package com.farafarin.mata.web.back.service.impl;

import com.farafarin.mata.common.constant.NotificationType;
import com.farafarin.mata.common.constant.UserSignUpRequestStatus;
import com.farafarin.mata.common.dao.AdminNotificationDao;
import com.farafarin.mata.common.dao.AdminNotificationTypeDao;
import com.farafarin.mata.common.dao.UserSignUpRequestDao;
import com.farafarin.mata.common.entity.AdminNotificationEntity;
import com.farafarin.mata.common.entity.AdminNotificationTypeEntity;
import com.farafarin.mata.common.entity.UserSignUpRequestEntity;
import com.farafarin.mata.common.security.model.UserProfile;
import com.farafarin.mata.common.util.AuthenticationUtil;
import com.farafarin.mata.common.util.DateUtil;
import com.farafarin.mata.web.back.model.UserSignUpRequestModel;
import com.farafarin.mata.web.back.service.UserSignUpRequestService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Asus on 11/7/2017.
 */
@Component
public class UserSignUpRequestServiceImpl implements UserSignUpRequestService {

    @Autowired
    private UserSignUpRequestDao userSignUpRequestDao;
    @Autowired
    private AdminNotificationDao adminNotificationDao;
    @Autowired
    private AdminNotificationTypeDao adminNotificationTypeDao;

    public UserSignUpRequestServiceImpl() {
    }

    @Override
    public void submitUserSignUpRequest(UserSignUpRequestModel userSignUpRequestModel) {
        UserSignUpRequestEntity userSignUpRequestEntity = new UserSignUpRequestEntity();
        userSignUpRequestEntity.setCreateDate(new Date());
        userSignUpRequestEntity.setStatus(UserSignUpRequestStatus.NEW.getStatus());
        BeanUtils.copyProperties(userSignUpRequestModel, userSignUpRequestEntity);
        userSignUpRequestDao.insertUserSignUpRequest(userSignUpRequestEntity);
        AdminNotificationEntity adminNotificationEntity = prepareAdminNotificationEntity(userSignUpRequestModel);
        adminNotificationDao.insertAdminNotification(adminNotificationEntity);

    }

    private AdminNotificationEntity prepareAdminNotificationEntity(UserSignUpRequestModel userSignUpRequestModel) {
        AdminNotificationEntity adminNotificationEntity = new AdminNotificationEntity();
        adminNotificationEntity.setNotificationType(NotificationType.SIGNUP.getNotificationType());
        String userName = userSignUpRequestModel.getFirstNameFa() + " " + userSignUpRequestModel.getLastNameFa();
        AdminNotificationTypeEntity adminNotificationTypeEntity =
                adminNotificationTypeDao.getAdminNotificationType(NotificationType.SIGNUP.getNotificationType());
        if(adminNotificationTypeEntity == null){
            //todo add exception here
        }
        String notificationFirstReplace = adminNotificationTypeEntity.getNotificationTemplate().replace("{param1}",userName);
        String notificationText = notificationFirstReplace.
                replace("{param2}",userSignUpRequestModel.getPhoneNumber());
        adminNotificationEntity.setNotificationText(notificationText);
        Date expireDate = DateUtil.addDays(new Date(),1);
        adminNotificationEntity.setExpireDate(expireDate);

        return adminNotificationEntity;
    }
}
