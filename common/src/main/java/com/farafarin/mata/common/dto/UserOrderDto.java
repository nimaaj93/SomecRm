package com.farafarin.mata.common.dto;

import com.farafarin.mata.common.entity.UserAddressEntity;
import com.farafarin.mata.common.util.DateUtil;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.Date;
import java.util.List;

/**
 * Created by Asus on 11/16/2017.
 */
public class UserOrderDto {
    private String providerOrderFirstNameFa;
    private String providerOrderLastNameFa;
    private String providerOrderFirstNameEn;
    private String providerOrderLastNameEn;
    private String phoneNumber;
    private Long userId;
    private String nationalCode;
    private String orderDate;
    private Long orderStatus;
    private String description;
    private Long amount;
    List<CategoryDto> categoryDtoList;
    private Long orderId;
    private UserAddressDto userAddressDto;
    private String orderNumber;
    private String uploadedFileName;

    public String getUploadedFileName() {
        return uploadedFileName;
    }

    public void setUploadedFileName(String uploadedFileName) {
        this.uploadedFileName = uploadedFileName;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public UserAddressDto getUserAddressDto() {
        return userAddressDto;
    }

    public void setUserAddressDto(UserAddressDto userAddressDto) {
        this.userAddressDto = userAddressDto;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProviderOrderFirstNameFa() {
        return providerOrderFirstNameFa;
    }

    public void setProviderOrderFirstNameFa(String providerOrderFirstNameFa) {
        this.providerOrderFirstNameFa = providerOrderFirstNameFa;
    }

    public String getProviderOrderLastNameFa() {
        return providerOrderLastNameFa;
    }

    public void setProviderOrderLastNameFa(String providerOrderLastNameFa) {
        this.providerOrderLastNameFa = providerOrderLastNameFa;
    }

    public String getProviderOrderFirstNameEn() {
        return providerOrderFirstNameEn;
    }

    public void setProviderOrderFirstNameEn(String providerOrderFirstNameEn) {
        this.providerOrderFirstNameEn = providerOrderFirstNameEn;
    }

    public String getProviderOrderLastNameEn() {
        return providerOrderLastNameEn;
    }

    public void setProviderOrderLastNameEn(String providerOrderLastNameEn) {
        this.providerOrderLastNameEn = providerOrderLastNameEn;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setJalaliOrderDate(Date date) {
        try {
            this.orderDate = DateUtil.convertGregorianDateToJalaliString(date);
        } catch (Exception e) {
            this.orderDate = "";
        }
    }

    public Long getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Long orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<CategoryDto> getCategoryDtoList() {
        return categoryDtoList;
    }

    public void setCategoryDtoList(List<CategoryDto> categoryDtoList) {
        this.categoryDtoList = categoryDtoList;
    }
}
