package com.farafarin.mata.common.entity;

import com.farafarin.mata.common.entity.BaseEntity;
import com.farafarin.mata.common.util.ApplicationConfig;
import com.farafarin.mata.common.util.DateUtil;
import com.farafarin.mata.common.util.Empty;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Asus on 11/2/2017.
 */
public class UserPersonalEntity extends BaseEntity {
    private Long userId;
    private String firstNameFa;
    private String lastNameFa;
    private String firstNameEn;
    private String lastNameEn;
    private String nationalCode;
    private String phoneNumber;
    private String email;
    private Date expireDate;
    private Long status;
    private Date birthDate;
    private Long userType;
    private Long corporateUserId;

    public Long getCorporateUserId() {
        return corporateUserId;
    }

    public void setCorporateUserId(Long corporateUserId) {
        this.corporateUserId = corporateUserId;
    }

    public Long getUserType() {
        return userType;
    }

    public void setUserType(Long userType) {
        this.userType = userType;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstNameFa() {
        return firstNameFa;
    }

    public void setFirstNameFa(String firstNameFa) {
        this.firstNameFa = firstNameFa;
    }

    public String getLastNameFa() {
        return lastNameFa;
    }

    public void setLastNameFa(String lastNameFa) {
        this.lastNameFa = lastNameFa;
    }

    public String getFirstNameEn() {
        return firstNameEn;
    }

    public void setFirstNameEn(String firstNameEn) {
        this.firstNameEn = firstNameEn;
    }

    public String getLastNameEn() {
        return lastNameEn;
    }

    public void setLastNameEn(String lastNameEn) {
        this.lastNameEn = lastNameEn;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public void setJalaliExpireDate(String jalaliExpireDate) {
        this.expireDate = DateUtil.convertStringJalahiDateToGregorianDate(jalaliExpireDate);
    }

}
