package com.farafarin.mata.common.dao;

import com.farafarin.mata.common.dto.CorporateUserDto;
import com.farafarin.mata.common.entity.CorporateUserEntity;
import com.farafarin.mata.common.searchobject.CorporateUserSO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Asus on 12/20/2017.
 */
@Component
public interface CorporateUserDao {

    void insertCorporateUser(@Param("model") CorporateUserEntity corporateUserEntity);

    CorporateUserDto retrieveCorporateUser(@Param("corporateUserId") Long corporateUserId);

    void updateStatus(@Param("status") Long status, @Param("id") Long id);

    Integer countCorporateUser(@Param("so") CorporateUserSO corporateUserSO);

    List<CorporateUserDto> getCorporateUsersList(@Param("so") CorporateUserSO corporateUserSO);

    void updateCorporateUser(@Param("model") CorporateUserEntity corporateUserEntity);
}
