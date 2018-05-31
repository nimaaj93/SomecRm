package com.farafarin.mata.common.dao;

import com.farafarin.mata.common.dto.AdminOrderLogDto;
import com.farafarin.mata.common.entity.AdminOrderLogEntity;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.LOCATION_FORWARD;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Asus on 5/14/2018.
 */
@Component
public interface OrderLogDao {
    void insertOrderLog(@Param("model")AdminOrderLogEntity model);
    List<AdminOrderLogDto> getOrderLogForUser(@Param("orderId") Long orderId);

}
