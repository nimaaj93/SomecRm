package com.farafarin.mata.common.constant;

/**
 * Created by Asus on 11/23/2017.
 */
public enum OfferStatus {
    NEW(600L),
    DELETED(601L);

private Long offerStatus;

    OfferStatus(Long offerStatus) {
        this.offerStatus = offerStatus;
    }

    public Long getOfferStatus() {

        return offerStatus;
    }
}
