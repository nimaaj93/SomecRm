package com.farafarin.mata.common.dao;

import com.farafarin.mata.common.entity.OrderFileEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * Created by Asus on 5/21/2018.
 */
@Component
public interface OrderFileDao {
    void insertOrderFile(@Param("model") OrderFileEntity orderFileEntity);
}
