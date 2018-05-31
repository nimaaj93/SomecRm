package com.farafarin.mata.admin.back.service;

import com.farafarin.mata.admin.back.model.CorporateUserSignUpModel;
import com.farafarin.mata.admin.back.model.CorporateUserUpdateModel;
import com.farafarin.mata.common.dto.CorporateUserDto;
import com.farafarin.mata.common.dto.UserPersonalDto;
import com.farafarin.mata.common.entity.CorporateUserEntity;
import com.farafarin.mata.common.searchobject.CorporateUserSO;

import java.util.List;

/**
 * Created by Asus on 12/20/2017.
 */
public interface CorporateUserSignUpService {

    CorporateUserEntity insertCorporateUser(CorporateUserSignUpModel corporateUserSignUpModel);

    CorporateUserDto getCorporateUser(Long corporateUserId);

    void activate(Long corporateUserId);

    void inactivate(Long corporateUserId);

    Integer countCorporateUsers(CorporateUserSO corporateUserSO);

    List<CorporateUserDto> getCorporateUsersList(CorporateUserSO corporateUserSO);

    void updateCorporateUser(CorporateUserUpdateModel corporateUserUpdateModel);

}
