package com.farafarin.mata.common.service;


import com.farafarin.mata.common.dto.CategoryDto;
import com.farafarin.mata.common.entity.OrderCategoryEntity;
import com.farafarin.mata.common.entity.UserAddressEntity;
import com.farafarin.mata.common.entity.UserPersonalEntity;
import com.farafarin.mata.common.model.AdminOrderModel;
import com.farafarin.mata.common.model.UserOrderModel;

import java.util.List;

/**
 * Created by Asus on 11/17/2017.
 */
public interface UserOrderService {
    void submitUserOrderRequest(UserOrderModel userOrderModel);
    void submitUserOrderRequest(AdminOrderModel adminOrderModel);
    UserPersonalEntity getUserInfo(String nationalCode,String phoneNumber);
    List<UserAddressEntity> getUserAddresses(String nationalCode,String phoneNumber);
    List<OrderCategoryEntity> getCategories(Long categoryId);
    List<UserAddressEntity> getUserAddresses();
}
