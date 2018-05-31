package com.farafarin.mata.common.dao;

import com.farafarin.mata.common.dto.UserOrderReportDto;
import com.farafarin.mata.common.dto.UserOrderUpdateEntity;
import com.farafarin.mata.common.entity.UserOrderEntity;
import com.farafarin.mata.common.entity.UserOrderReportEntity;
import com.farafarin.mata.common.searchobject.UserOrderSO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Asus on 11/16/2017.
 */
@Component
public interface UserOrderDao {
    List<UserOrderEntity> retrieveUserOrders(@Param("so") UserOrderSO so);

    void insertUserOrder(@Param("model") UserOrderEntity model);

    Long getUserOrderCount(@Param("so") UserOrderSO so);

    void updateUserOrder(@Param("model") UserOrderUpdateEntity model);

    UserOrderEntity retrieveUserOrderById(@Param("orderId") Long orderId);

    Long getOrderCountByUserIdAndStatus(@Param("userId") Long userId,@Param("orderStatusList") List<Long> orderStatusList);

    List<UserOrderReportEntity> getOrderByUserIdAndStatus(@Param("userId") Long userId,
                                                          @Param("orderStatusList") List<Long> orderStatusList,
                                                          @Param("pageSize") Integer pageSize,
                                                          @Param("offset") Integer offset);

    void cancelOrder(@Param("orderId") Long orderId);
}
