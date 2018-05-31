package com.farafarin.mata.common.constant;

/**
 * Created by Asus on 11/9/2017.
 */
public enum UserSignUpRequestStatus {
    DONE(200L),
    NEW(201L),
    IN_PROGRESS(202L),
    DELETED(203L)
    ;

    private Long status;

    UserSignUpRequestStatus (Long status){
        this.status = status;
    }

    public Long getStatus(){
        return status;
    }
}
