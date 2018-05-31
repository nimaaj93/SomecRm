package com.farafarin.mata.common.model;

import com.farafarin.mata.common.entity.BaseEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Asus on 11/23/2017.
 */
public class OfferUpdateModel extends BaseEntity {
    @NotNull
    private Long offerId;
    private String header;
    @Size(max = 1000)
    private String description;
    private String expireDate;
    private Long availableCount;
    private Long offerStatus;

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

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public Long getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(Long availableCount) {
        this.availableCount = availableCount;
    }

    public Long getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(Long offerStatus) {
        this.offerStatus = offerStatus;
    }
}
