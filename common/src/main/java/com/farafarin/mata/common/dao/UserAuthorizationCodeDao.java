package com.farafarin.mata.common.dao;

import com.farafarin.mata.common.dto.UserAuthorizationCodeDto;
import com.farafarin.mata.common.entity.UserAuthorizationCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * Created by K550 VX on 12/4/2017.
 */
@Component
public interface UserAuthorizationCodeDao {

    void create(@Param("userAuthorizationCode") UserAuthorizationCode userAuthorizationCode);

    void updateStatus(@Param("id") Long id);

    UserAuthorizationCodeDto getLatestGeneratedToken(@Param("userId") Long userId);

}
