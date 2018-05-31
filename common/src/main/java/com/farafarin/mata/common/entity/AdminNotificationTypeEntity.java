package com.farafarin.mata.common.entity;

/**
 * Created by Asus on 12/1/2017.
 */
public class AdminNotificationTypeEntity extends BaseEntity {
    private Long notificationTypeId;
    private Long notificationType;
    private String notificationTemplate;

    public Long getNotificationTypeId() {
        return notificationTypeId;
    }

    public void setNotificationTypeId(Long notificationTypeId) {
        this.notificationTypeId = notificationTypeId;
    }

    public Long getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(Long notificationType) {
        this.notificationType = notificationType;
    }

    public String getNotificationTemplate() {
        return notificationTemplate;
    }

    public void setNotificationTemplate(String notificationTemplate) {
        this.notificationTemplate = notificationTemplate;
    }
}
