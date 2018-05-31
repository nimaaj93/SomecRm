package com.farafarin.mata.web.back.security;

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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by K550 VX on 10/27/2017.
 */
@Component("webAuthenticationProvider")
public class MataWebAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserAuthenticationDao userAuthenticationDao;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName().trim();
        String password = authentication.getCredentials().toString().trim();
        String hashedPassword = AuthenticationUtil.hashPassword(password);
        UserProfile userProfile
                = userAuthenticationDao.authenticate(username,hashedPassword, UserType.MATA_USER.getUserType());

        if (userProfile == null) {
            throw new BadCredentialsException("bad.credential");
        }
        if (userProfile.getStatus().equals(UserStatus.INACTIVE.getStatus())) {
            throw new UserIsInactiveException("user.inactive");
        }
        if (userProfile.getExpireDate().before(new Date())) {
            throw new UserIsExpiredException("user.expired");
        }

        if (userProfile.getCorporateUserEntity() != null &&
                userProfile.getCorporateUserEntity().getCorporateUserId() != null) {

            if (userProfile.getCorporateUserEntity().getExpireDate().before(new Date())) {
                throw new UserIsExpiredException("user.expired");
            }

            if (userProfile.getCorporateUserEntity().getStatus().equals(UserStatus.INACTIVE.getStatus())) {
                throw new UserIsInactiveException("user.inactive");
            }

        }

        userProfile.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(userProfile);

        return userProfile;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
