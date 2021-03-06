package com.farafarin.mata.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by K550 VX on 11/14/2017.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST,reason = "user.expired")
public class UserIsExpiredException extends AuthenticationException {
    public UserIsExpiredException(String msg) {
        super(msg);
    }
}
