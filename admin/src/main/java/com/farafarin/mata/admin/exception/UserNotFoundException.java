package com.farafarin.mata.admin.exception;

import com.farafarin.mata.common.exception.MataBusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by K550 VX on 11/6/2017.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "user.not.found")
public class UserNotFoundException extends MataBusinessException {
}
