package com.farafarin.mata.common.entity;

import java.util.Date;

/**
 * Created by Asus on 12/1/2017.
 */
public class AdminNotificationEntity extends BaseEntity {
    private Long notificationId;
    private Long notificationType;
    private Date expireDate;
    private String notificationText;

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public Long getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(Long notificationType) {
        this.notificationType = notificationType;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }
}
