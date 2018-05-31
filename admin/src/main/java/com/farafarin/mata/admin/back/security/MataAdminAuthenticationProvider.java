package com.farafarin.mata.admin.back.security;

import com.farafarin.mata.common.constant.UserStatus;
import com.farafarin.mata.common.constant.UserType;
import com.farafarin.mata.common.dao.UserAuthenticationDao;
import com.farafarin.mata.common.exception.UserIsExpiredException;
import com.farafarin.mata.common.exception.UserIsInactiveException;
import com.farafarin.mata.common.security.model.UserProfile;
import com.farafarin.mata.common.util.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by K550 VX on 10/31/2017.
 */
@Component("adminAuthenticationProvider")
public class MataAdminAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserAuthenticationDao userAuthenticationDao;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName().trim();
        String password = authentication.getCredentials().toString().trim();
        String hashedPassword = AuthenticationUtil.hashPassword(password);
        UserProfile userProfile
                = userAuthenticationDao.authenticate(username,hashedPassword, UserType.ADMIN.getUserType());

        if (userProfile == null) {
            throw new BadCredentialsException("invalid username or pass");
        }
        if (userProfile.getStatus().equals(UserStatus.INACTIVE.getStatus())) {
            throw new UserIsInactiveException("user.inactive");
        }
        if (userProfile.getExpireDate().before(new Date())) {
            throw new UserIsExpiredException("user.expired");
        }
        userProfile.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(userProfile);

        return userProfile;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}
