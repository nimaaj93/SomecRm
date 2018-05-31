package com.farafarin.mata.admin.back.model;

import javax.validation.constraints.NotNull;

/**
 * Created by Asus on 11/19/2017.
 */
public class UserMessageModel {
    private String nationalCode;
    private String phoneNumber;
    @NotNull
    private String header;
    @NotNull
    private String description;

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
}
