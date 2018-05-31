package com.farafarin.mata.admin.back.service.impl;

import com.farafarin.mata.admin.back.model.CorporateUserSignUpModel;
import com.farafarin.mata.admin.back.model.CorporateUserUpdateModel;
import com.farafarin.mata.admin.back.service.CorporateUserSignUpService;
import com.farafarin.mata.common.constant.CorporateUserStatus;
import com.farafarin.mata.common.dao.CorporateUserDao;
import com.farafarin.mata.common.dao.UserDao;
import com.farafarin.mata.common.dto.CorporateUserDto;
import com.farafarin.mata.common.dto.UserPersonalDto;
import com.farafarin.mata.common.entity.CorporateUserEntity;
import com.farafarin.mata.common.searchobject.CorporateUserSO;
import com.farafarin.mata.common.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by Asus on 12/20/2017.
 */
@Component
public class CorporateUserSignUpServiceImpl implements CorporateUserSignUpService {

    @Autowired
    private CorporateUserDao corporateUserDao;
    @Autowired
    private UserDao userDao;

    @Override
    public CorporateUserEntity insertCorporateUser(CorporateUserSignUpModel corporateUserSignUpModel) {

        CorporateUserEntity corporateUserEntity = prepareCorporateUserEntity(corporateUserSignUpModel);
        corporateUserDao.insertCorporateUser(corporateUserEntity);
        return corporateUserEntity;
    }

    @Override
    public CorporateUserDto getCorporateUser(Long corporateUserId) {
        return corporateUserDao.retrieveCorporateUser(corporateUserId);
    }

    @Override
    public void activate(Long corporateUserId) {
        corporateUserDao.updateStatus(CorporateUserStatus.ACTIVE.getStatus(),corporateUserId);
    }

    @Override
    public void inactivate(Long corporateUserId) {
        corporateUserDao.updateStatus(CorporateUserStatus.INACTIVE.getStatus(),corporateUserId);
    }

    @Override
    public Integer countCorporateUsers(CorporateUserSO corporateUserSO) {
        return corporateUserDao.countCorporateUser(corporateUserSO);
    }

    @Override
    public List<CorporateUserDto> getCorporateUsersList(CorporateUserSO corporateUserSO) {
        return corporateUserDao.getCorporateUsersList(corporateUserSO);
    }

    @Override
    public void updateCorporateUser(CorporateUserUpdateModel corporateUserUpdateModel) {
        CorporateUserEntity corporateUserEntity = new CorporateUserEntity();
        BeanUtils.copyProperties(corporateUserUpdateModel,corporateUserEntity);
        Date expireDate = DateUtil.convertStringJalahiDateToGregorianDate(corporateUserUpdateModel.getExpireDate());
        corporateUserEntity.setExpireDate(expireDate);
        corporateUserDao.updateCorporateUser(corporateUserEntity);
    }

    private CorporateUserEntity prepareCorporateUserEntity(CorporateUserSignUpModel corporateUserSignUpModel) {
        CorporateUserEntity corporateUserEntity = new CorporateUserEntity();
        BeanUtils.copyProperties(corporateUserSignUpModel,corporateUserEntity);
        corporateUserEntity.setStatus(CorporateUserStatus.ACTIVE.getStatus());
        Date expireDate = DateUtil.convertStringJalahiDateToGregorianDate(corporateUserSignUpModel.getExpireDate());
        corporateUserEntity.setExpireDate(expireDate);
        return corporateUserEntity;
    }
}
