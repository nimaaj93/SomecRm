package com.farafarin.mata.common.dao;


import com.farafarin.mata.common.dto.UserPersonalDto;
import com.farafarin.mata.common.entity.UserPersonalEntity;
import com.farafarin.mata.common.model.SearchQuery;
import com.farafarin.mata.common.searchobject.UserSO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Asus on 11/2/2017.
 */

@Component
public interface UserDao {

    void insertUserPersonalInfo(@Param("model") UserPersonalEntity model);

    List<UserPersonalDto> retrieveUsers(@Param("so") UserSO so);

    UserPersonalEntity retrieveUserByNationalCode (@Param("nationalCode") String nationalCode);

    UserPersonalDto retrieveUserDtoByNationalCode (@Param("nationalCode") String nationalCode);

    void editUserPersonalInfo(@Param("model") UserPersonalEntity model);

    void updateUserStatus(@Param("nationalCode") String nationalCode,
                          @Param("status") Long status);

    Long getUsersCount(@Param("so") UserSO so);

    List<UserPersonalDto> retrieveNearExpireMataUsers(@Param("userType") Long userType,
                                                      @Param("offset") Integer offset,
                                                      @Param("pageSize") Integer pageSize);

    Long getNearExpireUsersCount(@Param("userType") Long userType);

    UserPersonalEntity retrieveUserByNationalCodeAndPhoneNumber(@Param("nationalCode") String nationalCode,
                                                                @Param("phoneNumber") String phoneNumber);
    Long getActiveUsersCount(@Param("userType") Long userType);

    List<UserPersonalDto> getCorporateUsersForParent(@Param("corporateUserId") Long corporateUserId);

    List<UserPersonalDto> searchUsers(@Param("model") SearchQuery searchQuery);

}
