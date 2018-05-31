package com.farafarin.mata.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by K550 VX on 12/4/2017.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "username.not.found")
public class UsernameNotFoundException extends MataBusinessException {
}
