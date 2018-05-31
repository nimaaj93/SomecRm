package com.farafarin.mata.admin.back.service;

import com.farafarin.mata.common.dto.UserPersonalDto;
import com.farafarin.mata.admin.back.model.UserPersonalModel;
import com.farafarin.mata.common.searchobject.UserSO;

import java.util.List;

/**
 * Created by Asus on 11/12/2017.
 */
public interface AdminUserManagementService {
    Long getAdminUserCount(UserSO userSO);
    List<UserPersonalDto> retrieveAdminUsers(UserSO userSO);
    void disableUser(String nationalCode);
    void enableUser(String nationalCode);
    void resetAdminUserPassword(String newPassword,String confirmPassword,String nationalCode);
    void editUserPersonalInfo(UserPersonalModel userPersonalModel);

}
