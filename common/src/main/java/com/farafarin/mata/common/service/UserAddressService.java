package com.farafarin.mata.common.service;

import com.farafarin.mata.common.entity.UserAddressEntity;
import com.farafarin.mata.common.model.UserAddressModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Asus on 11/21/2017.
 */
public interface UserAddressService {
    void insertUserAddress(UserAddressModel userAddressModel,Long userId);
    List<UserAddressEntity> getUserAddress(@Param("userId") Long userId);
    void deleteAddress(Long addressId);
}
