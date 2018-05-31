package com.farafarin.mata.web.back.dto;

import com.farafarin.mata.common.entity.UserAddressEntity;

import java.util.List;

/**
 * Created by Asus on 11/25/2017.
 */
public class UserContactInformationDto {
    private String phoneNumber;
    private String email;
    private List<UserAddressEntity> userAddressEntity;

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

    public List<UserAddressEntity> getUserAddressEntity() {
        return userAddressEntity;
    }

    public void setUserAddressEntity(List<UserAddressEntity> userAddressEntity) {
        this.userAddressEntity = userAddressEntity;
    }
}
