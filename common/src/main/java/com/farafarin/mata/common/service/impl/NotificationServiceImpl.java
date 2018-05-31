package com.farafarin.mata.common.service.impl;

import com.farafarin.mata.common.constant.NotificationType;
import com.farafarin.mata.common.dao.AdminNotificationDao;
import com.farafarin.mata.common.dao.AdminNotificationTypeDao;
import com.farafarin.mata.common.dto.AdminNotificationDto;
import com.farafarin.mata.common.entity.AdminNotificationEntity;
import com.farafarin.mata.common.entity.AdminNotificationTypeEntity;
import com.farafarin.mata.common.searchobject.AdminNotificationSO;
import com.farafarin.mata.common.security.model.UserProfile;
import com.farafarin.mata.common.service.NotificationService;
import com.farafarin.mata.common.util.AuthenticationUtil;
import com.farafarin.mata.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by Asus on 12/1/2017.
 */
@Component
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private AdminNotificationDao adminNotificationDao;
    @Autowired
    private AdminNotificationTypeDao adminNotificationTypeDao;

    @Override
    public List<AdminNotificationDto> getNotifications(AdminNotificationSO adminNotificationSO) {
        return adminNotificationDao.getNotifications(adminNotificationSO);
    }

    @Override
    public Integer getNotificationsCount() {
        return adminNotificationDao.getNotificationsCount();
    }

    @Override
    public void sendInformationChnageNotification() {
        AdminNotificationEntity adminNotificationEntity = prepareAdminNotificationEntity();
        adminNotificationDao.insertAdminNotification(adminNotificationEntity);

    }

    private AdminNotificationEntity prepareAdminNotificationEntity(){
        AdminNotificationEntity adminNotificationEntity = new AdminNotificationEntity();
        adminNotificationEntity.setNotificationType(NotificationType.CHANGEINFO.getNotificationType());
        UserProfile currentUser = AuthenticationUtil.getCurrentUser();
        String userName = currentUser.getFirstNameFa() + " " +currentUser.getLastNameFa();
        AdminNotificationTypeEntity adminNotificationTypeEntity =
                adminNotificationTypeDao.getAdminNotificationType(NotificationType.CHANGEINFO.getNotificationType());
        if(adminNotificationTypeEntity == null){
            //todo add exception here
        }
        String notificationFirstParamReplace = adminNotificationTypeEntity.getNotificationTemplate().replace("{param1}",userName);
        String notificationText = notificationFirstParamReplace.replace("{param2}",currentUser.getNationalCode());
        adminNotificationEntity.setNotificationText(notificationText);
        Date expireDate = DateUtil.addDays(new Date(),1);
        adminNotificationEntity.setExpireDate(expireDate);

        return adminNotificationEntity;
    }
}
