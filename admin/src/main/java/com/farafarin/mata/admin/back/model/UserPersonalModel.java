package com.farafarin.mata.admin.back.model;

import com.farafarin.mata.common.validation.annotation.Phone;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Asus on 11/1/2017.
 */
public class UserPersonalModel {
    @NotEmpty
    @Size(max = 50)
    private String firstNameFa;
    @NotEmpty
    @Size(max = 50)
    private String lastNameFa;
    @NotEmpty
    @Size(max = 50)
    private String firstNameEn;
    @NotEmpty
    @Size(max = 50)
    private String lastNameEn;
    @Size(min = 10,max = 10)
    @NotEmpty
    private String nationalCode;
    @Size(max = 11)
    @Phone
    @NotEmpty
    private String phoneNumber;
    @Email
    @Size(max = 150)
    private String email;
    @NotEmpty
    private String birthDate;
    @NotEmpty
    private String expireDateStr;

    private Long corporateUserId;

    public Long getCorporateUserId() {
        return corporateUserId;
    }

    public void setCorporateUserId(Long corporateUserId) {
        this.corporateUserId = corporateUserId;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
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

    public String getExpireDateStr() {
        return expireDateStr;
    }

    public void setExpireDateStr(String expireDateStr) {
        this.expireDateStr = expireDateStr;
    }
}
