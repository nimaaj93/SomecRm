package com.farafarin.mata.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by K550 VX on 12/18/2017.
 */
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "order.submition.exception")
public class OrderSubmitionException extends MataBusinessException {

    public OrderSubmitionException() {
    }

    public OrderSubmitionException(Throwable cause) {
        super(cause);
    }
}
