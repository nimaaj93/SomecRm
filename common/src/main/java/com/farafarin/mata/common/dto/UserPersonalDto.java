package com.farafarin.mata.common.dto;

import com.farafarin.mata.common.util.DateUtil;

import java.util.Date;

/**
 * Created by Asus on 11/8/2017.
 */
public class UserPersonalDto {

    private String firstNameFa;
    private String lastNameFa;
    private String firstNameEn;
    private String lastNameEn;
    private String nationalCode;
    private String phoneNumber;
    private String email;
    private String birthDate;
    private Long status;
    private String expireDate;
    private Long corporateUserId;
    private Boolean nearExpireDate = false;
    private String corporateUserNameFa;
    private String corporateUserNameEn;
    private Long balance;
    private Long userId;
    private String searchAnchor;
    private String searchTitle;

    public Boolean getNearExpireDate() {
        return nearExpireDate;
    }

    public void setNearExpireDate(Boolean nearExpireDate) {
        this.nearExpireDate = nearExpireDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getCorporateUserId() {
        return corporateUserId;
    }

    public void setCorporateUserId(Long corporateUserId) {
        this.corporateUserId = corporateUserId;
    }

    public void setJalaliBirthDate(String date){
        String jalaliBirthDate = DateUtil.convertGregorianStringToJalaliString(date);
        this.birthDate = jalaliBirthDate;
    }

    public void setJalaliExpireDate(String date){
        String jalaliExpireDate = DateUtil.convertGregorianStringToJalaliString(date);
        this.expireDate = jalaliExpireDate;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getCorporateUserNameFa() {
        return corporateUserNameFa;
    }

    public void setCorporateUserNameFa(String corporateUserNameFa) {
        this.corporateUserNameFa = corporateUserNameFa;
    }

    public String getCorporateUserNameEn() {
        return corporateUserNameEn;
    }

    public void setCorporateUserNameEn(String corporateUserNameEn) {
        this.corporateUserNameEn = corporateUserNameEn;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSearchAnchor() {
        return searchAnchor;
    }

    public void setSearchAnchor(String searchAnchor) {
        this.searchAnchor = searchAnchor;
    }

    public String getSearchTitle() {
        return searchTitle;
    }

    public void setSearchTitle(String searchTitle) {
        this.searchTitle = searchTitle;
    }
}
