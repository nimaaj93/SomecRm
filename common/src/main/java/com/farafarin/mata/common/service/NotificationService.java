package com.farafarin.mata.common.service;

import com.farafarin.mata.common.dto.AdminNotificationDto;
import com.farafarin.mata.common.searchobject.AdminNotificationSO;

import java.util.List;

/**
 * Created by Asus on 12/1/2017.
 */
public interface NotificationService {
    List<AdminNotificationDto> getNotifications(AdminNotificationSO adminNotificationSO);

    Integer getNotificationsCount();

    void sendInformationChnageNotification();
}
