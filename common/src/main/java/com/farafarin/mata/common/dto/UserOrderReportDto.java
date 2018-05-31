package com.farafarin.mata.common.dto;

import com.farafarin.mata.common.dto.CategoryDto;
import com.farafarin.mata.common.dto.UserAddressDto;

import java.util.Date;
import java.util.List;

/**
 * Created by Asus on 11/23/2017.
 */
public class UserOrderReportDto {

    private Long orderId;
    private String orderDate;
    private Long orderStatus;
    private String description;
    private Long amount;
    List<CategoryDto> categoryDtoList;
    private UserAddressDto userAddressDto;
    private String orderNumber;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Long getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Long orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public List<CategoryDto> getCategoryDtoList() {
        return categoryDtoList;
    }

    public void setCategoryDtoList(List<CategoryDto> categoryDtoList) {
        this.categoryDtoList = categoryDtoList;
    }

    public UserAddressDto getUserAddressDto() {
        return userAddressDto;
    }

    public void setUserAddressDto(UserAddressDto userAddressDto) {
        this.userAddressDto = userAddressDto;
    }
}
