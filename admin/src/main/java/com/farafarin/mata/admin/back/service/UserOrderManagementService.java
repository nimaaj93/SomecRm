package com.farafarin.mata.admin.back.service;

import com.farafarin.mata.common.dto.AdminOrderLogDto;
import com.farafarin.mata.common.entity.AdminOrderLogEntity;
import com.farafarin.mata.common.model.AdminOrderLog;
import com.farafarin.mata.common.model.UserOrderUpdateModel;
import com.farafarin.mata.common.searchobject.UserOrderSO;
import com.farafarin.mata.common.dto.UserOrderDto;

import java.util.List;

/**
 * Created by Asus on 11/16/2017.
 */
public interface UserOrderManagementService {
    List<UserOrderDto> getUserOrders(UserOrderSO userOrderSO);
    Long getUserOrderCount(UserOrderSO userOrderSO);
    void updateUserOrder(UserOrderUpdateModel userOrderUpdateModel);
//    void payOrder(Long orderId);
    void addOrderLog(AdminOrderLog adminOrderLog);
    List<AdminOrderLogDto> getOrderLogs(Long orderId);
}
