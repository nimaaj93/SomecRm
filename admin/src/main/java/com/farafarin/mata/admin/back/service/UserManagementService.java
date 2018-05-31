package com.farafarin.mata.admin.back.service;

import com.farafarin.mata.common.dto.UserPersonalDto;
import com.farafarin.mata.admin.back.model.UserPersonalModel;
import com.farafarin.mata.common.model.SearchQuery;
import com.farafarin.mata.common.searchobject.UserSO;

import java.util.List;

/**
 * Created by Asus on 11/6/2017.
 */
public interface UserManagementService {
    List<UserPersonalDto> retrieveUsers(UserSO userSO);
    void editUserPersonalInfo(UserPersonalModel userPersonalModel);
    void disableUser(String nationalCode);
    void enableUser(String nationalCode);
    Long getUsersCount(UserSO userSO);
    List<UserPersonalDto> getNearExpireMataUsers(Integer pageNumber,Integer pageSize);
    Long getNearExpireUsersCount();
    Long getActiveUsersCount();
    List<UserPersonalDto> searchUsers(SearchQuery searchQuery);
}
