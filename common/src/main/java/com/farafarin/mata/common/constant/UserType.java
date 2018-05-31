package com.farafarin.mata.common.constant;

/**
 * Created by Asus on 11/11/2017.
 */
public enum UserType {
    MATA_USER(300L),
    ADMIN(301L);

    private Long userType;

    UserType(Long userType){
        this.userType = userType;
    }

    public Long getUserType() {
        return userType;
    }
}
