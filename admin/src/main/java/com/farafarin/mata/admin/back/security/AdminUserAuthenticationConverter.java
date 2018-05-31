package com.farafarin.mata.admin.back.security;

import com.farafarin.mata.common.logback.annotation.Log;
import com.farafarin.mata.common.security.model.UserCredential;
import com.farafarin.mata.common.security.model.UserProfile;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by K550 VX on 11/17/2017.
 */
@Component
@Qualifier("adminUserAuthenticationConverter")
public class AdminUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

    private @Log Logger LOG;
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("mataAdminUserDetailsService")
    @Override
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        super.setUserDetailsService(userDetailsService);
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
        if (map.containsKey("user_name")) {
            String username = (String) map.get("user_name");
            UserProfile userProfile = (UserProfile) userDetailsService.loadUserByUsername(username);
            userProfile.setAuthenticated(true);
            return userProfile;
        }
        throw new IllegalArgumentException("user_name not found!");
    }
}
