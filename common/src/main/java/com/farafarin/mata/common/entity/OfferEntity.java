package com.farafarin.mata.common.entity;

import com.farafarin.mata.common.entity.BaseEntity;

import java.util.Date;

/**
 * Created by Asus on 11/21/2017.
 */
public class OfferEntity extends BaseEntity {
    private Long offerId;
    private String header;
    private String description;
    private Date offerExpireDate;
    private Long offerCount;
    private Long senderUserId;
    private Long offerStatus;

    public Long getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(Long offerStatus) {
        this.offerStatus = offerStatus;
    }

    public Long getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(Long senderUserId) {
        this.senderUserId = senderUserId;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getOfferExpireDate() {
        return offerExpireDate;
    }

    public void setOfferExpireDate(Date offerExpireDate) {
        this.offerExpireDate = offerExpireDate;
    }

    public Long getOfferCount() {
        return offerCount;
    }

    public void setOfferCount(Long offerCount) {
        this.offerCount = offerCount;
    }
}
