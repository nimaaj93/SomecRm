package com.farafarin.mata.web.back.security;

import com.farafarin.mata.common.constant.UserStatus;
import com.farafarin.mata.common.constant.UserType;
import com.farafarin.mata.common.dao.UserAuthenticationDao;
import com.farafarin.mata.common.dao.UserAuthorizationCodeDao;
import com.farafarin.mata.common.dto.UserAuthorizationCodeDto;
import com.farafarin.mata.common.exception.UserIsExpiredException;
import com.farafarin.mata.common.exception.UserIsInactiveException;
import com.farafarin.mata.common.exception.UsernameNotFoundException;
import com.farafarin.mata.common.security.model.UserProfile;
import com.farafarin.mata.common.util.Empty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by K550 VX on 12/4/2017.
 */
@Component("webSmsAuthenticationProvider")
public class WebSmsAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserAuthenticationDao userAuthenticationDao;
    @Autowired
    private UserAuthorizationCodeDao userAuthorizationCodeDao;
    @Autowired
    @Qualifier("mataWebUserDetailsService")
    private UserDetailsService userDetailsService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName().trim();
        String password = authentication.getCredentials().toString().trim();
//        String hashedPassword = AuthenticationUtil.hashPassword(password);
        UserProfile userProfile =
                (UserProfile) userDetailsService.loadUserByUsername(username);

        if (!Empty.isNotEmpty(userProfile)) {
            throw new UsernameNotFoundException();
        }

        UserAuthorizationCodeDto userAuthorizationCodeDto
                = userAuthorizationCodeDao.getLatestGeneratedToken(userProfile.getUserId());

        if (userAuthorizationCodeDto == null) {
            throw new IllegalStateException("No authorization code generated yet");
        }

        if (!userAuthorizationCodeDto.getAuthorizationCode().equals(password)) {
            throw new BadCredentialsException("invalid.authorization.code");
        }

        if (userAuthorizationCodeDto.getExpireDate().before(new Date())) {
            throw new BadCredentialsException("invalid.authorization.code");
        }

        userProfile.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(userProfile);

        return userProfile;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(AuthorizationCodeToken.class);
    }
}
