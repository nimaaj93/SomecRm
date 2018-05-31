package com.farafarin.mata.admin.back.service;

import com.farafarin.mata.common.dto.UserSignUpRequestDto;
import com.farafarin.mata.common.searchobject.UserSO;

import java.util.List;

/**
 * Created by Asus on 11/9/2017.
 */
public interface UserSignUpRequestManagementService {

    List<UserSignUpRequestDto> getUserSignUpRequestList(UserSO userSO);
    Long getUserSignUpRequestCount(String phoneNumber,String firstNameFa,String lastNameFa);
    void deleteUserSignUpRequest(String phoneNumber,Long signUpRequestId);
    void changeUserSignUpRequestToSeen(String phoneNumber,Long signUpRequestId);
    void changeUserSignUpRequestToDone(String phoneNumber,Long signUpRequestId);
    Long getNewUserSignUpRequestCount();
}
