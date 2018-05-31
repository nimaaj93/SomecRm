package com.farafarin.mata.admin.exception;

import com.farafarin.mata.common.exception.MataBusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Asus on 11/5/2017.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST,reason = "national.code.duplicate")
public class UserNationalCodeDuplicateException extends MataBusinessException {
}
