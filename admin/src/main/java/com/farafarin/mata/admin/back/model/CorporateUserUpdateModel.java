package com.farafarin.mata.admin.back.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by K550 VX on 1/3/2018.
 */
public class CorporateUserUpdateModel {

    @NotEmpty
    @Size(max = 50)
    private String nameFa;
    @NotEmpty
    @Size(max = 50)
    private String nameEn;
    @Size(max = 50)
    private String companyId;
    private String expireDate;
    @NotEmpty
    @Size(max = 30)
    private String phoneNumber;
    @NotEmpty
    @Size(max = 100)
    private String cityFa;
    @Size(max = 100)
    private String cityEn;
    @NotEmpty
    @Size(max = 500)
    private String addressFa;
    @Size(max = 500)
    private String addressEn;
    @NotNull
    private Long corporateUserId;

    public String getNameFa() {
        return nameFa;
    }

    public void setNameFa(String nameFa) {
        this.nameFa = nameFa;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
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

    public Long getCorporateUserId() {
        return corporateUserId;
    }

    public void setCorporateUserId(Long corporateUserId) {
        this.corporateUserId = corporateUserId;
    }
}
