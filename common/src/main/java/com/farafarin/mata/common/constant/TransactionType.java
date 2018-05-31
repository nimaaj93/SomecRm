package com.farafarin.mata.common.constant;

/**
 * Created by K550 VX on 1/15/2018.
 */
public enum TransactionType {

    ADMIN_CREATED(800L),
    USER_CREATED(801L),
    FROM_PG(802L);

    private Long id;

    TransactionType(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
