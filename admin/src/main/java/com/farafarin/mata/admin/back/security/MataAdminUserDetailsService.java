package com.farafarin.mata.admin.back.security;

import com.farafarin.mata.common.constant.UserType;
import com.farafarin.mata.common.dao.UserAuthenticationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by K550 VX on 10/31/2017.
 */
@Service("mataAdminUserDetailsService")
public class MataAdminUserDetailsService implements UserDetailsService {

    @Autowired
    private UserAuthenticationDao userAuthenticationDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails userDetails = userAuthenticationDao.getUserProfileByUsername(username, UserType.ADMIN.getUserType());

        if (userDetails == null) {
            throw new UsernameNotFoundException("Username not found in database");
        }

        return userDetails;
    }
}
