package com.farafarin.mata.admin.exception;

import com.farafarin.mata.common.exception.MataBusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Asus on 11/11/2017.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST,reason = "password.confirm.not.matched")
public class PasswordAndConfirmNotMatchException extends MataBusinessException {
}
