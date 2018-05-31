package com.farafarin.mata.web.back.service;

import com.farafarin.mata.common.dto.UserAuthorizationCodeDto;

/**
 * Created by K550 VX on 12/4/2017.
 */
public interface UserAuthorizationCodeService {

    String createAuthorizationCode(String username);

    UserAuthorizationCodeDto getCreatedAuthorization(String username);

}
