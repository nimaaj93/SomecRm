package com.farafarin.mata.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by K550 VX on 1/22/2018.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "invalid.input.data")
public class InvalidInputDataException extends MataBusinessException {
}
