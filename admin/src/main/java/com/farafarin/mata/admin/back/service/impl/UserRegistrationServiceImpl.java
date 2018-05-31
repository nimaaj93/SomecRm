package com.farafarin.mata.admin.back.service.impl;

import com.farafarin.mata.admin.back.dao.UserRoleDao;
import com.farafarin.mata.admin.back.entity.UserRoleEntity;
import com.farafarin.mata.common.constant.UserStatus;
import com.farafarin.mata.common.dao.UserAddressDao;
import com.farafarin.mata.common.constant.UserType;
import com.farafarin.mata.common.dao.UserAuthenticationDao;
import com.farafarin.mata.common.dao.UserCreditDao;
import com.farafarin.mata.common.dao.UserDao;
import com.farafarin.mata.common.entity.UserAddressEntity;
import com.farafarin.mata.common.entity.UserAuthenticationEntity;
import com.farafarin.mata.common.entity.UserCreditEntity;
import com.farafarin.mata.common.entity.UserPersonalEntity;
import com.farafarin.mata.admin.back.model.*;
import com.farafarin.mata.admin.back.service.UserRegistrationService;
import com.farafarin.mata.admin.exception.UserCreationException;
import com.farafarin.mata.admin.exception.UserNationalCodeDuplicateException;
import com.farafarin.mata.admin.exception.UserPhoneNumberDuplicateException;
import com.farafarin.mata.common.exception.MataBusinessException;
import com.farafarin.mata.common.logback.annotation.Log;
import com.farafarin.mata.common.model.UserAddressModel;
import com.farafarin.mata.common.util.ApplicationConfig;
import com.farafarin.mata.common.util.AuthenticationUtil;
import com.farafarin.mata.common.util.DateUtil;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Asus on 11/2/2017.
 */
@Component
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private static @Log
    Logger LOG;

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserAddressDao userAddressDao;
    @Autowired
    private UserAuthenticationDao userAuthenticationDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private UserCreditDao userCreditDao;

    @Override
    @Transactional
    public void insertUser(SignUpModel signUpModel) {
        validateUserUniqueness(signUpModel);
        insertUserInfo(signUpModel);
    }

    private void insertUserInfo(SignUpModel signUpModel) {
        UserPersonalEntity userPersonalEntity = new UserPersonalEntity();
        Date birthDate = DateUtil.convertStringJalahiDateToGregorianDate(signUpModel.getUserPersonalModel().getBirthDate());
        userPersonalEntity.setBirthDate(birthDate);
        userPersonalEntity.setStatus(UserStatus.ACTIVE.getStatus());
        userPersonalEntity.setUserType(UserType.MATA_USER.getUserType());
        userPersonalEntity.setJalaliExpireDate(signUpModel.getUserPersonalModel().getExpireDateStr());
        BeanUtils.copyProperties(signUpModel.getUserPersonalModel(), userPersonalEntity);
        userDao.insertUserPersonalInfo(userPersonalEntity);

        UserAuthenticationModel userAuthenticationModel = prepareUserAuthenticationModel(userPersonalEntity);
        UserAuthenticationEntity userAuthenticationEntity = new UserAuthenticationEntity();
        userAuthenticationEntity.setUserId(userPersonalEntity.getUserId());
        BeanUtils.copyProperties(userAuthenticationModel, userAuthenticationEntity);
        userAuthenticationDao.insertUserAuthentication(userAuthenticationEntity);

        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUserId(userPersonalEntity.getUserId());
        userRoleEntity.setRoleId(Long.valueOf(ApplicationConfig.getProperty("user.role.id")));
        userRoleDao.insertUserRole(userRoleEntity);

        for (UserAddressModel userAddress : signUpModel.getUserAddressInfoList()) {
            UserAddressEntity userAddressInfoEntity = new UserAddressEntity();
            userAddressInfoEntity.setUserId(userPersonalEntity.getUserId());
            BeanUtils.copyProperties(userAddress, userAddressInfoEntity);
            userAddressDao.insertUserAddresses(userAddressInfoEntity);

        }

        UserCreditEntity userCreditEntity = new UserCreditEntity();

        userCreditEntity.setUserId(userPersonalEntity.getUserId());
        userCreditEntity.setBalance(0L);

        userCreditDao.createCreditForUser(userCreditEntity);

    }

    private UserAuthenticationModel prepareUserAuthenticationModel(UserPersonalEntity userPersonalEntity) {
        UserAuthenticationModel userAuthenticationModel = new UserAuthenticationModel();
        userAuthenticationModel.setUserName(userPersonalEntity.getNationalCode());
        userAuthenticationModel.setPassword(AuthenticationUtil.hashPassword("defaultpass"));
        return userAuthenticationModel;
    }

    private void validateUserUniqueness(SignUpModel signUpModel) {
        UserPersonalEntity userPersonalEntity = userDao.retrieveUserByNationalCode(signUpModel.getUserPersonalModel().getNationalCode());
        if (userPersonalEntity != null) {
            if (userPersonalEntity.getNationalCode().equals(signUpModel.getUserPersonalModel().getNationalCode())) {
                throw new UserNationalCodeDuplicateException();
            }
        }
    }

    @Override
    public UserPersonalModel getUserByNationalCode(String nationalCode) {
        UserPersonalModel userPersonalModel = null;
        UserPersonalEntity userPersonalEntity = userDao.retrieveUserByNationalCode(nationalCode);
        if (userPersonalEntity != null) {
            userPersonalModel = new UserPersonalModel();
            String birthDate = DateUtil.convertGregorianDateToJalaliString(userPersonalEntity.getBirthDate());
            userPersonalModel.setBirthDate(birthDate);
            String expireDate = DateUtil.convertGregorianDateToJalaliString(userPersonalEntity.getExpireDate());
            userPersonalModel.setExpireDateStr(expireDate);
            BeanUtils.copyProperties(userPersonalEntity, userPersonalModel);
        }
        return userPersonalModel;
    }

}
