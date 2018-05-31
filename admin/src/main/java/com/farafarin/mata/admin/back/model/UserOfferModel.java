package com.farafarin.mata.admin.back.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Asus on 11/21/2017.
 */
public class UserOfferModel {
    @NotNull
    private String header;
    @NotNull
    @Size(max = 1000)
    private String description;
    @NotNull
    private String offerExpireDate;

    private Long offerCount;

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

    public String getOfferExpireDate() {
        return offerExpireDate;
    }

    public void setOfferExpireDate(String offerExpireDate) {
        this.offerExpireDate = offerExpireDate;
    }

    public Long getOfferCount() {
        return offerCount;
    }

    public void setOfferCount(Long offerCount) {
        this.offerCount = offerCount;
    }
}
