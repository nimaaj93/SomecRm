package com.farafarin.mata.admin.back.service.impl;

import com.farafarin.mata.common.constant.UserStatus;
import com.farafarin.mata.common.dao.UserDao;
import com.farafarin.mata.admin.back.dao.UserRoleDao;
import com.farafarin.mata.common.entity.UserPersonalEntity;
import com.farafarin.mata.admin.back.entity.UserRoleEntity;
import com.farafarin.mata.admin.back.model.AdminSignUpModel;
import com.farafarin.mata.admin.back.model.UserAuthenticationModel;
import com.farafarin.mata.admin.back.model.UserPersonalModel;
import com.farafarin.mata.admin.back.service.AdminUserRegistrationService;
import com.farafarin.mata.admin.exception.PasswordAndConfirmNotMatchException;
import com.farafarin.mata.admin.exception.UserCreationException;
import com.farafarin.mata.admin.exception.UserNationalCodeDuplicateException;
import com.farafarin.mata.admin.exception.UserPhoneNumberDuplicateException;
import com.farafarin.mata.common.constant.UserType;
import com.farafarin.mata.common.dao.UserAuthenticationDao;
import com.farafarin.mata.common.entity.UserAuthenticationEntity;
import com.farafarin.mata.common.logback.annotation.Log;
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
 * Created by Asus on 11/11/2017.
 */
@Component
public class AdminUserRegistrationServiceImpl implements AdminUserRegistrationService {

    private static @Log
    Logger LOG;

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserAuthenticationDao userAuthenticationDao;

    @Autowired
    private UserRoleDao userRoleDao;


    @Override
    @Transactional
//    @PreAuthorize("hasAuthority('superAdmin')")
    public void insertAdminUser(AdminSignUpModel adminSignUpModel) {
        validateUserUniqueness(adminSignUpModel.getNationalCode(), adminSignUpModel.getPhoneNumber());
        validatePassword(adminSignUpModel.getPassword(), adminSignUpModel.getConfirmPassword());
        insertUserInfo(adminSignUpModel);
    }

    @Override
//    @PreAuthorize(value = "hasAuthority('superAdmin')")
    public UserPersonalModel getAdminUserByNationalCode(String nationalCode) {
        UserPersonalModel userPersonalModel = null;
        UserPersonalEntity userPersonalEntity = userDao.retrieveUserByNationalCode(nationalCode);
        if (userPersonalEntity != null) {
            userPersonalModel = new UserPersonalModel();
            String birthDate = DateUtil.convertGregorianDateToJalaliString(userPersonalEntity.getBirthDate());
            userPersonalModel.setBirthDate(birthDate);
            BeanUtils.copyProperties(userPersonalEntity, userPersonalModel);
        }
        return userPersonalModel;
    }

    private void validateUserUniqueness(String nationalCode,String phoneNumber) {
        UserPersonalEntity userPersonalEntity = userDao.retrieveUserByNationalCode(nationalCode);
        if (userPersonalEntity != null) {
            if (userPersonalEntity.getNationalCode().equals(nationalCode)) {
                throw new UserNationalCodeDuplicateException();
            }
        }
    }

    private void insertUserInfo(AdminSignUpModel adminSignUpModel){

        UserPersonalEntity userPersonalEntity = getUserPersonalEntity(adminSignUpModel);
        userDao.insertUserPersonalInfo(userPersonalEntity);

        UserAuthenticationModel userAuthenticationModel =
                prepareUserAuthenticationModel(userPersonalEntity,adminSignUpModel.getPassword());
        UserAuthenticationEntity userAuthenticationEntity = new UserAuthenticationEntity();
        userAuthenticationEntity.setUserId(userPersonalEntity.getUserId());
        BeanUtils.copyProperties(userAuthenticationModel, userAuthenticationEntity);
        userAuthenticationDao.insertUserAuthentication(userAuthenticationEntity);

        if(adminSignUpModel.isSuperAdmin()){
            userRoleDao.insertUserRole(prepareUserRoleEntity(userPersonalEntity.getUserId(),
                    Long.parseLong(ApplicationConfig.getProperty("super.admin.role.id"))));
            userRoleDao.insertUserRole(prepareUserRoleEntity(userPersonalEntity.getUserId(),
                    Long.parseLong(ApplicationConfig.getProperty("admin.role.id"))));

        }else{
            userRoleDao.insertUserRole(prepareUserRoleEntity(userPersonalEntity.getUserId(),
                    Long.parseLong(ApplicationConfig.getProperty("admin.role.id"))));
        }
    }

    private UserPersonalEntity getUserPersonalEntity(AdminSignUpModel adminSignUpModel) {
        UserPersonalEntity userPersonalEntity = new UserPersonalEntity();
        Date birthDate = DateUtil.convertStringJalahiDateToGregorianDate(adminSignUpModel.getBirthDate());
        Date expireDate = DateUtil.convertStringJalahiDateToGregorianDate(adminSignUpModel.getExpireDateStr());
        userPersonalEntity.setBirthDate(birthDate);
        userPersonalEntity.setExpireDate(expireDate);
        userPersonalEntity.setStatus(UserStatus.ACTIVE.getStatus());
        userPersonalEntity.setUserType(UserType.ADMIN.getUserType());
        BeanUtils.copyProperties(adminSignUpModel, userPersonalEntity);
        return userPersonalEntity;
    }

    private UserAuthenticationModel prepareUserAuthenticationModel(UserPersonalEntity userPersonalEntity,String password) {
        UserAuthenticationModel userAuthenticationModel = new UserAuthenticationModel();
        userAuthenticationModel.setUserName(userPersonalEntity.getNationalCode());
        String hashedPassword = AuthenticationUtil.hashPassword(password);
        userAuthenticationModel.setPassword(hashedPassword);
        return userAuthenticationModel;
    }

    private UserRoleEntity prepareUserRoleEntity(Long userId,Long roleId){
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setRoleId(roleId);
        userRoleEntity.setUserId(userId);
        return userRoleEntity;
    }

    private void validatePassword(String password,String confirmPassword){
        if(!password.equals(confirmPassword)){
            throw  new PasswordAndConfirmNotMatchException();
        }

    }
}
