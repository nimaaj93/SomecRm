package com.farafarin.mata.web.back.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by K550 VX on 12/4/2017.
 */
public class AuthorizationCodeToken extends UsernamePasswordAuthenticationToken {


    public AuthorizationCodeToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public AuthorizationCodeToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
