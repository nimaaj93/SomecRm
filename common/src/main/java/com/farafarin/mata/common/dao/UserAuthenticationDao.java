package com.farafarin.mata.common.dao;


import com.farafarin.mata.common.entity.UserAuthenticationEntity;
import com.farafarin.mata.common.security.model.UserProfile;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * Created by Asus on 11/2/2017.
 */

@Component
public interface UserAuthenticationDao {

    void insertUserAuthentication(@Param("model")UserAuthenticationEntity model);

    void resetPassword(@Param("userId") Long userId,@Param("newPassword") String newPassword);

    UserProfile authenticate(@Param("username") String username,
                             @Param("password") String password,
                             @Param("userType") Long userType);

    UserProfile getUserProfileByUsername(@Param("username") String username,
                                         @Param("userType") Long userType);

}
