package com.farafarin.mata.common.constant;

/**
 * Created by Asus on 11/5/2017.
 */
public enum UserStatus {
    ACTIVE(100L),
    INACTIVE(101L),
    ;

    private Long status;

    UserStatus(Long status){
        this.status = status;
    }

    public Long getStatus() {
        return status;
    }

    public static UserStatus getInstanceFromCode(Long code) {
        for (UserStatus userStatus : UserStatus.values()) {
            if (userStatus.getStatus().equals(code)) {
                return userStatus;
            }
        }
        return null;
    }

}
