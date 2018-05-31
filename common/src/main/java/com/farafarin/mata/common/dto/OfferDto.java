package com.farafarin.mata.common.dto;

import com.farafarin.mata.common.util.DateUtil;

import java.util.Date;

/**
 * Created by Asus on 11/22/2017.
 */
public class OfferDto {

    private String promotionHeader;
    private String description;
    private String expireDate;
    private String offerAvailableCount;
    private String senderFirstNameFa;
    private String senderLastNameFa;
    private String senderFirstNameEn;
    private String senderLastNameEn;
    private String createDate;
    private Long offerStatus;
    private Long offerId;

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public Long getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(Long offerStatus) {
        this.offerStatus = offerStatus;
    }

    public String getPromotionHeader() {
        return promotionHeader;
    }

    public void setPromotionHeader(String promotionHeader) {
        this.promotionHeader = promotionHeader;
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

    public String getOfferAvailableCount() {
        return offerAvailableCount;
    }

    public void setOfferAvailableCount(String offerAvailableCount) {
        this.offerAvailableCount = offerAvailableCount;
    }

    public String getSenderFirstNameFa() {
        return senderFirstNameFa;
    }

    public void setSenderFirstNameFa(String senderFirstNameFa) {
        this.senderFirstNameFa = senderFirstNameFa;
    }

    public String getSenderLastNameFa() {
        return senderLastNameFa;
    }

    public void setSenderLastNameFa(String senderLastNameFa) {
        this.senderLastNameFa = senderLastNameFa;
    }

    public String getSenderFirstNameEn() {
        return senderFirstNameEn;
    }

    public void setSenderFirstNameEn(String senderFirstNameEn) {
        this.senderFirstNameEn = senderFirstNameEn;
    }

    public String getSenderLastNameEn() {
        return senderLastNameEn;
    }

    public void setSenderLastNameEn(String senderLastNameEn) {
        this.senderLastNameEn = senderLastNameEn;
    }

    public void setJalaliExpireDate(String date){
        String jalaliSendDate = DateUtil.convertGregorianStringToJalaliString(date);
        this.expireDate = jalaliSendDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setJalaliCreateDate(Date date){
        try {
            String jalaliCreateDate = DateUtil.convertGregorianDateToJalaliString(date);
            this.createDate = jalaliCreateDate;
        } catch (Exception e) {
            this.createDate = "";
        }
    }
}
