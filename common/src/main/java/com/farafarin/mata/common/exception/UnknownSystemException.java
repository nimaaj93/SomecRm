package com.farafarin.mata.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Asus on 11/7/2017.
 */
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR,reason = "unknown.exception")
public class UnknownSystemException extends MataBusinessException {

    public UnknownSystemException() {
    }

    public UnknownSystemException(String msg) {
        super(msg);
    }

    public UnknownSystemException(Throwable cause) {
        super(cause);
    }
}
