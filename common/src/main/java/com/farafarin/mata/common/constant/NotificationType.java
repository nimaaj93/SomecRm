package com.farafarin.mata.common.constant;

/**
 * Created by Asus on 12/1/2017.
 */
public enum NotificationType {
    SIGNUP(700L),
    CHANGEINFO(701L),
    ORDER(702L);

    NotificationType(Long notificationType) {
        this.notificationType = notificationType;
    }

    private Long notificationType;

    public Long getNotificationType() {
        return notificationType;
    }
}
