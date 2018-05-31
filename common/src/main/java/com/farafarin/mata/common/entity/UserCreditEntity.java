package com.farafarin.mata.common.entity;

/**
 * Created by K550 VX on 1/16/2018.
 */
public class UserCreditEntity extends BaseEntity {

    private Long userId;
    private Long balance;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
