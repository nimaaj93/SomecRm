package com.farafarin.mata.common.constant;

/**
 * Created by K550 VX on 12/11/2017.
 */
public enum UserNotificationMessageType {

    AUTHORIZATION_CODE(1L),
    SUBMIT_ORDER(2L),
    DONE_ORDER(3L),
    SEND_ORDER(4L),
    VERIFIED_ORDER(5L),
    CANCELED_ORDER(6L)
    ;

    private Long id;

    UserNotificationMessageType(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
