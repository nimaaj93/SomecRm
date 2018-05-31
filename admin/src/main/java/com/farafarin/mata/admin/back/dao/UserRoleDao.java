package com.farafarin.mata.admin.back.dao;

import com.farafarin.mata.admin.back.entity.UserRoleEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * Created by Asus on 11/11/2017.
 */
@Component
public interface UserRoleDao {
    void insertUserRole(@Param("model") UserRoleEntity model);
}
