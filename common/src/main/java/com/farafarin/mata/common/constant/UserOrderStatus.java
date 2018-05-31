package com.farafarin.mata.common.constant;

/**
 * Created by Asus on 11/17/2017.
 */
public enum  UserOrderStatus {
    SIGN(400L),
    DONE(401L),
    SEND(402L),
    VERIFIED(403L),
    CANCELED(404L)
    ;

    private Long userOrderStatus;

    UserOrderStatus(Long userOrderStatus){
        this.userOrderStatus = userOrderStatus;
    }

    public Long getUserOrderStatus() {
        return userOrderStatus;
    }
}
