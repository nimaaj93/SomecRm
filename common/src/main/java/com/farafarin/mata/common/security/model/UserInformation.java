package com.farafarin.mata.common.security.model;

import com.farafarin.mata.common.util.DateUtil;

import java.util.Date;

/**
 * Created by K550 VX on 11/13/2017.
 */
public class UserInformation {

    private String firstNameFa;
    private String lastNameFa;
    private String firstNameEn;
    private String lastNameEn;
    private String nationalCode;
    private String phoneNumber;
    private String email;
    private String birthDate;

    public UserInformation() {
    }

    public UserInformation(UserProfile userProfile) {
        this.firstNameFa = userProfile.getFirstNameFa();
        this.lastNameFa = userProfile.getLastNameFa();
        this.firstNameEn = userProfile.getFirstNameEn();
        this.lastNameEn = userProfile.getLastNameEn();
        this.nationalCode = userProfile.getNationalCode();
        this.phoneNumber = userProfile.getPhoneNumber();
        this.email = userProfile.getEmail();

        try {
            this.birthDate =
                    DateUtil.convertGregorianDateToJalaliString(userProfile.getBirthDate());
        } catch (Exception e) {
            this.birthDate = "";
        }
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
}
