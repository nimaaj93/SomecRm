package com.farafarin.mata.common.entity;

import com.farafarin.mata.common.util.ApplicationConfig;
import com.farafarin.mata.common.util.Empty;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Asus on 12/20/2017.
 */
public class CorporateUserEntity extends BaseEntity {
    private Long corporateUserId;
    private String nameEn;
    private String nameFa;
    private String companyId;
    private Long status;
    private Date expireDate;
    private String phoneNumber;
    private String cityFa;
    private String cityEn;
    private String addressFa;
    private String addressEn;

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

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        if(!Empty.isNotEmpty(expireDate)){
            Calendar c = Calendar.getInstance();
            Integer expireDateRange = Integer.parseInt(ApplicationConfig.getProperty("expire.date.range"));
            c.add(Calendar.YEAR, expireDateRange);
            expireDate = c.getTime();
        }
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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
