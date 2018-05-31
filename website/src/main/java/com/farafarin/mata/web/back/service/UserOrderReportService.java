package com.farafarin.mata.web.back.service;

import com.farafarin.mata.common.dto.UserOrderReportDto;

import java.util.List;

/**
 * Created by Asus on 11/23/2017.
 */
public interface UserOrderReportService {

    Long getUserOrderCount(List<Long> orderStatusList);
    List<UserOrderReportDto> getUserOrders(List<Long> orderStatusList, Integer pageSize, Integer pageNumber);
    void cancelOrder(Long orderId);
}
