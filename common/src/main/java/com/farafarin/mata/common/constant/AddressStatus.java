package com.farafarin.mata.common.constant;

/**
 * Created by K550 VX on 1/22/2018.
 */
public enum  AddressStatus {
    ACTIVE(100L),
    DELETED(101L);

    private Long id;

    AddressStatus(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
