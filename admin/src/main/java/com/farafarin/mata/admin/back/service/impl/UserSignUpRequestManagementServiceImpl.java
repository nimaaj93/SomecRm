package com.farafarin.mata.admin.back.service.impl;

import com.farafarin.mata.common.constant.UserSignUpRequestStatus;
import com.farafarin.mata.common.dto.UserSignUpRequestDto;
import com.farafarin.mata.admin.back.service.UserSignUpRequestManagementService;
import com.farafarin.mata.common.dao.UserSignUpRequestDao;
import com.farafarin.mata.common.searchobject.UserSO;
import com.farafarin.mata.common.security.model.UserProfile;
import com.farafarin.mata.common.util.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Asus on 11/9/2017.
 */
@Component
public class UserSignUpRequestManagementServiceImpl implements UserSignUpRequestManagementService {

    @Autowired
    private UserSignUpRequestDao userSignUpRequestDao;

    @Override
    public List<UserSignUpRequestDto> getUserSignUpRequestList(UserSO userSO) {
        List<Long> statusList = getStatus();
        return userSignUpRequestDao.retrieveUserSignUpRequestList(userSO,statusList);
    }

    private List<Long> getStatus() {
        List<Long> statusList = new ArrayList<>();
        statusList.add(UserSignUpRequestStatus.NEW.getStatus());
        statusList.add(UserSignUpRequestStatus.IN_PROGRESS.getStatus());
        statusList.add(UserSignUpRequestStatus.DONE.getStatus());
        return statusList;
    }

    @Override
    public Long getUserSignUpRequestCount(String phoneNumber,String firstNameFa,String lastNameFa) {
        return userSignUpRequestDao.getUserSignUpRequestCount(phoneNumber,firstNameFa,lastNameFa);
    }

    @Override
    public void deleteUserSignUpRequest(String phoneNumber, Long signUpRequestId) {
        UserProfile currentUser = AuthenticationUtil.getCurrentUser();
        userSignUpRequestDao.changeUserSignUpRequestStatus(phoneNumber, signUpRequestId,
                UserSignUpRequestStatus.DELETED.getStatus(),currentUser.getUserId());
    }

    @Override
    public void changeUserSignUpRequestToSeen(String phoneNumber, Long signUpRequestId) {
        UserProfile currentUser = AuthenticationUtil.getCurrentUser();
        userSignUpRequestDao.changeUserSignUpRequestStatus(phoneNumber, signUpRequestId,
                UserSignUpRequestStatus.IN_PROGRESS.getStatus(),currentUser.getUserId());
    }

    @Override
    public void changeUserSignUpRequestToDone(String phoneNumber, Long signUpRequestId) {
        UserProfile currentUser =AuthenticationUtil.getCurrentUser();
        userSignUpRequestDao.changeUserSignUpRequestStatus(phoneNumber, signUpRequestId,
                UserSignUpRequestStatus.DONE.getStatus(),currentUser.getUserId());
    }

    @Override
    public Long getNewUserSignUpRequestCount() {
        return userSignUpRequestDao.getNewUserSignUpRequestCount();
    }
}
