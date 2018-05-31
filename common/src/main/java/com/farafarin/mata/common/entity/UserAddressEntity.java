package com.farafarin.mata.common.entity;

import com.farafarin.mata.common.entity.BaseEntity;

/**
 * Created by Asus on 11/2/2017.
 */
    public class UserAddressEntity extends BaseEntity {
    private Long addressId;
    private String addressFa;
    private String addressEn;
    private String cityFa;
    private String cityEn;
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
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
}
