package com.farafarin.mata.common.dto;

import com.farafarin.mata.common.util.DateUtil;

import java.util.Date;

/**
 * Created by Asus on 12/1/2017.
 */
public class AdminNotificationDto {

    private Long id;
    private Long notificationType;
    private String notificationText;
    private String notificationDateStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(Long notificationType) {
        this.notificationType = notificationType;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }

    public String getNotificationDateStr() {
        return notificationDateStr;
    }

    public void setNotificationDateStr(String notificationDateStr) {
        this.notificationDateStr = notificationDateStr;
    }

    public void setNotificationDate(Date notificationDate) {
        try {
            this.notificationDateStr = DateUtil.convertGregorianDateToJalaliString(notificationDate);
        } catch (Exception e) {
            notificationDateStr = "";
        }
    }
}
