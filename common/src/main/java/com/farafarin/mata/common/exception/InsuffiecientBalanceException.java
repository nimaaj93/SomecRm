package com.farafarin.mata.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by K550 VX on 1/16/2018.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "insufficient.balance")
public class InsuffiecientBalanceException extends MataBusinessException {
}
