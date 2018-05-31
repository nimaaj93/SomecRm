package com.farafarin.mata.common.constant;

/**
 * Created by Asus on 12/22/2017.
 */
public enum CorporateUserStatus {
    ACTIVE(100L),
    INACTIVE(101L),
    ;

    private Long status;

    CorporateUserStatus(Long status){
        this.status = status;
    }

    public Long getStatus() {
        return status;
    }

    public static CorporateUserStatus getInstanceFromCode(Long code) {
        for (CorporateUserStatus corporateUserStatus : CorporateUserStatus.values()) {
            if (corporateUserStatus.getStatus().equals(code)) {
                return corporateUserStatus;
            }
        }
        return null;
    }
}
