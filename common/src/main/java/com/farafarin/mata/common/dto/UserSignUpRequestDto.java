package com.farafarin.mata.common.dto;

import com.farafarin.mata.common.util.DateUtil;

import java.util.Date;

/**
 * Created by Asus on 11/8/2017.
 */
public class UserSignUpRequestDto {

    private String firstNameFa;
    private String lastNameFa;
    private String phoneNumber;
    private String email;
    private String learnMethod;
    private String description;
    private String requestDate;
    private Long status;
    private Long signUpRequestId;

    public Long getSignUpRequestId() {
        return signUpRequestId;
    }

    public void setSignUpRequestId(Long signUpRequestId) {
        this.signUpRequestId = signUpRequestId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public void setJalaliRequestDate(Date date) {
        try {
            this.requestDate = DateUtil.convertGregorianDateToJalaliString(date);
        } catch (Exception e) {
            this.requestDate = "";
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

    public String getLearnMethod() {
        return learnMethod;
    }

    public void setLearnMethod(String learnMethod) {
        this.learnMethod = learnMethod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
