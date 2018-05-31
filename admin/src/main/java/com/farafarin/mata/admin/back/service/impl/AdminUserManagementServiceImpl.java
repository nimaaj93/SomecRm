package com.farafarin.mata.admin.back.service.impl;

import com.farafarin.mata.common.constant.UserStatus;
import com.farafarin.mata.common.dao.UserDao;
import com.farafarin.mata.common.dto.UserPersonalDto;
import com.farafarin.mata.common.entity.UserPersonalEntity;
import com.farafarin.mata.admin.back.model.UserPersonalModel;
import com.farafarin.mata.admin.back.service.AdminUserManagementService;
import com.farafarin.mata.admin.exception.PasswordAndConfirmNotMatchException;
import com.farafarin.mata.common.constant.UserType;
import com.farafarin.mata.common.dao.UserAuthenticationDao;
import com.farafarin.mata.common.searchobject.UserSO;
import com.farafarin.mata.common.util.AuthenticationUtil;
import com.farafarin.mata.common.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by Asus on 11/12/2017.
 */
@Component
public class AdminUserManagementServiceImpl implements AdminUserManagementService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserAuthenticationDao userAuthenticationDao;

    @Override
    public Long getAdminUserCount(UserSO userSO) {
        userSO.setUserType(UserType.ADMIN.getUserType());
        return userDao.getUsersCount(userSO);
    }

    @Override
    public List<UserPersonalDto> retrieveAdminUsers(UserSO userSO) {
        userSO.setUserType(UserType.ADMIN.getUserType());
        return userDao.retrieveUsers(userSO);
    }

    @Override
    public void disableUser(String nationalCode) {
        userDao.updateUserStatus(nationalCode, UserStatus.INACTIVE.getStatus());
    }

    @Override
    public void enableUser(String nationalCode) {
        userDao.updateUserStatus(nationalCode, UserStatus.ACTIVE.getStatus());
    }

    @Override
    public void resetAdminUserPassword(String newPassword,String confirmPassword,String nationalCode) {
        if(!newPassword.equals(confirmPassword)){
            throw  new PasswordAndConfirmNotMatchException();
        }
        UserPersonalEntity userPersonalEntity = userDao.retrieveUserByNationalCode(nationalCode);
        String hashedPassword = AuthenticationUtil.hashPassword(newPassword);
        userAuthenticationDao.resetPassword(userPersonalEntity.getUserId(),hashedPassword);
    }

    @Override
    public void editUserPersonalInfo(UserPersonalModel userPersonalModel) {
        UserPersonalEntity userPersonalEntity = new UserPersonalEntity();
        Date birthDate = DateUtil.convertStringJalahiDateToGregorianDate(userPersonalModel.getBirthDate());
        userPersonalEntity.setBirthDate(birthDate);
        BeanUtils.copyProperties(userPersonalModel, userPersonalEntity);
        userDao.editUserPersonalInfo(userPersonalEntity);
    }
}
