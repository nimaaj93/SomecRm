package com.farafarin.mata.common.dao;

import com.farafarin.mata.common.dto.UserSignUpRequestDto;
import com.farafarin.mata.common.entity.UserSignUpRequestEntity;
import com.farafarin.mata.common.searchobject.UserSO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Asus on 11/7/2017.
 */
@Component
public interface UserSignUpRequestDao {
    void insertUserSignUpRequest(@Param("model") UserSignUpRequestEntity model);
        List<UserSignUpRequestDto> retrieveUserSignUpRequestList(@Param("so")UserSO so,
                                                                 @Param("statusList") List<Long> statusList);

    Long getUserSignUpRequestCount(@Param("phoneNumber") String phoneNumber,
                                   @Param("firstNameFa") String firstNameFa,
                                   @Param("lastNameFa") String lastNameFa);

    void changeUserSignUpRequestStatus(@Param("phoneNumber") String phoneNumber,
                                       @Param("signUpRequestId") Long signUpRequestId, @Param("status") Long status,
                                       @Param("investigationUserId") Long investigationUserId);
    Long getNewUserSignUpRequestCount();
}

