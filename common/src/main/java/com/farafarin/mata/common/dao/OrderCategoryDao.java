package com.farafarin.mata.common.dao;


import com.farafarin.mata.common.dto.CategoryDto;
import com.farafarin.mata.common.entity.OrderCategoryEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Asus on 11/16/2017.
 */
@Component
public interface OrderCategoryDao {

    OrderCategoryEntity getOrderCategory(@Param("id") Long id);
    List<OrderCategoryEntity> getOrderCategoryByParentId(@Param("parentId") Long parentId);
}
