package com.farafarin.mata.common.entity;

/**
 * Created by Asus on 11/27/2017.
 */
public class UserOfferEntity extends BaseEntity {
    private Long userOfferId;
    private Long offerId;
    private Long userId;

    public Long getUserOfferId() {
        return userOfferId;
    }

    public void setUserOfferId(Long userOfferId) {
        this.userOfferId = userOfferId;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
