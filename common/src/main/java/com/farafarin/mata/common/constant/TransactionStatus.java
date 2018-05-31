package com.farafarin.mata.common.constant;

/**
 * Created by K550 VX on 1/15/2018.
 */
public enum TransactionStatus {

    SUCCESSFUL(900L),
    FAILED(901L);

    private Long id;

    TransactionStatus(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
