package com.farafarin.mata.web.back.security;

import com.farafarin.mata.common.constant.UserType;
import com.farafarin.mata.common.dao.UserAuthenticationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by K550 VX on 11/26/2017.
 */
@Service("mataWebUserDetailsService")
public class MataWebUserDetailsService implements UserDetailsService {

    @Autowired
    private UserAuthenticationDao userAuthenticationDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails userDetails = userAuthenticationDao.getUserProfileByUsername(username, UserType.MATA_USER.getUserType());

        if (userDetails == null) {
            throw new UsernameNotFoundException("Username not found in database");
        }

        return userDetails;
    }

}
