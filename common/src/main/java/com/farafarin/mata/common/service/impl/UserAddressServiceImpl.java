package com.farafarin.mata.common.service.impl;

import com.farafarin.mata.common.dao.UserAddressDao;
import com.farafarin.mata.common.entity.UserAddressEntity;
import com.farafarin.mata.common.model.UserAddressModel;
import com.farafarin.mata.common.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Asus on 11/21/2017.
 */
@Component
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressDao userAddressDao;

    @Override
    public void insertUserAddress(UserAddressModel userAddressModel, Long userId) {
        UserAddressEntity userAddressEntity = prepareUserAddressEntity(userAddressModel,userId);
        userAddressDao.insertUserAddresses(userAddressEntity);
    }

    @Override
    public List<UserAddressEntity> getUserAddress(Long userId) {
        return userAddressDao.retrieveUserAddresses(userId);
    }

    @Override
    public void deleteAddress(Long addressId) {
        userAddressDao.deleteAddress(addressId);
    }

    private UserAddressEntity prepareUserAddressEntity(UserAddressModel userAddressModel, Long userId){
        UserAddressEntity userAddressEntity = new UserAddressEntity();
        userAddressEntity.setUserId(userId);
        userAddressEntity.setAddressFa(userAddressModel.getAddressFa());
        userAddressEntity.setAddressEn(userAddressModel.getAddressEn());
        userAddressEntity.setCityFa(userAddressModel.getCityFa());
        userAddressEntity.setCityEn(userAddressModel.getCityEn());
        return userAddressEntity;
    }
}
