package com.farafarin.mata.admin.back.service.impl;

import com.farafarin.mata.common.constant.UserStatus;
import com.farafarin.mata.common.dao.UserDao;
import com.farafarin.mata.common.dto.UserPersonalDto;
import com.farafarin.mata.common.entity.UserPersonalEntity;
import com.farafarin.mata.admin.back.model.UserPersonalModel;
import com.farafarin.mata.admin.back.service.UserManagementService;
import com.farafarin.mata.common.constant.UserType;
import com.farafarin.mata.common.model.SearchQuery;
import com.farafarin.mata.common.searchobject.UserSO;
import com.farafarin.mata.common.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Asus on 11/6/2017.
 */
@Component
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<UserPersonalDto> retrieveUsers(UserSO userSO) {
        userSO.setUserType(UserType.MATA_USER.getUserType());
        List<UserPersonalDto> userPersonalList = userDao.retrieveUsers(userSO);
        for (UserPersonalDto userPersonalDto : userPersonalList) {
            Date expireDate = DateUtil.convertStringJalahiDateToGregorianDate(userPersonalDto.getExpireDate());
            int diffInDays = (int)( (expireDate.getTime() - new Date().getTime())
                    / (1000 * 60 * 60 * 24) );
            if(diffInDays < 7){
                userPersonalDto.setNearExpireDate(true);
            }
        }

        return userPersonalList;
    }

    @Override
    public void editUserPersonalInfo(UserPersonalModel userPersonalModel) {
        UserPersonalEntity userPersonalEntity = new UserPersonalEntity();
        Date birthDate = DateUtil.convertStringJalahiDateToGregorianDate(userPersonalModel.getBirthDate());
        userPersonalEntity.setBirthDate(birthDate);
        BeanUtils.copyProperties(userPersonalModel, userPersonalEntity);
        userDao.editUserPersonalInfo(userPersonalEntity);
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
    public Long getUsersCount(UserSO userSO) {
        userSO.setUserType(UserType.MATA_USER.getUserType());
        Long userCount = userDao.getUsersCount(userSO);
        return userCount;
    }

    @Override
    public List<UserPersonalDto> getNearExpireMataUsers(Integer pageNumber,Integer pageSize) {
        Integer offset = pageNumber * pageSize;
        return userDao.retrieveNearExpireMataUsers(UserType.MATA_USER.getUserType(),offset ,pageSize);
    }

    @Override
    public Long getNearExpireUsersCount() {
        return userDao.getNearExpireUsersCount(UserType.MATA_USER.getUserType());
    }

    @Override
    public Long getActiveUsersCount() {
        return userDao.getActiveUsersCount(UserType.MATA_USER.getUserType());
    }

    @Override
    public List<UserPersonalDto> searchUsers(SearchQuery searchQuery) {
        List<UserPersonalDto> searchUsers = userDao.searchUsers(searchQuery);

        for (UserPersonalDto userPersonalDto : searchUsers) {
            userPersonalDto.setSearchAnchor(userPersonalDto.getFirstNameFa() + " " + userPersonalDto.getLastNameFa());
            userPersonalDto.setSearchTitle(userPersonalDto.getNationalCode());
        }

        return searchUsers;
    }
}
