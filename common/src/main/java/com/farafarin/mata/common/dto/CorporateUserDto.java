package com.farafarin.mata.common.dto;

import com.farafarin.mata.common.util.DateUtil;

import java.util.Date;

/**
 * Created by K550 VX on 12/24/2017.
 */
public class CorporateUserDto {

    private Long corporateUserId;
    private String nameEn;
    private String nameFa;
    private String companyId;
    private Long status;
    private String expireDate;
    private String phoneNumber;
    private String cityFa;
    private String cityEn;
    private String addressFa;
    private String addressEn;

    public Long getCorporateUserId() {
        return corporateUserId;
    }

    public void setCorporateUserId(Long corporateUserId) {
        this.corporateUserId = corporateUserId;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameFa() {
        return nameFa;
    }

    public void setNameFa(String nameFa) {
        this.nameFa = nameFa;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCityFa() {
        return cityFa;
    }

    public void setCityFa(String cityFa) {
        this.cityFa = cityFa;
    }

    public String getCityEn() {
        return cityEn;
    }

    public void setCityEn(String cityEn) {
        this.cityEn = cityEn;
    }

    public String getAddressFa() {
        return addressFa;
    }

    public void setAddressFa(String addressFa) {
        this.addressFa = addressFa;
    }

    public String getAddressEn() {
        return addressEn;
    }

    public void setAddressEn(String addressEn) {
        this.addressEn = addressEn;
    }

    public void setJalaliExpireDate(String date){
        String jalaliSendDate = DateUtil.convertGregorianStringToJalaliString(date);
        this.expireDate = jalaliSendDate;
    }

}
