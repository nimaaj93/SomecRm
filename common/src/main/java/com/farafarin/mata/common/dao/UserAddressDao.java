package com.farafarin.mata.common.dao;

import com.farafarin.mata.common.entity.UserAddressEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Asus on 11/2/2017.
 */

@Component
public interface UserAddressDao {
    void insertUserAddresses(@Param("model") UserAddressEntity model);
    List<UserAddressEntity> retrieveUserAddresses(@Param("userId") Long userId);
    void deleteAddress(@Param("addressId") Long addressId);
}
