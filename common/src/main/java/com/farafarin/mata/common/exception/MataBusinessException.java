package com.farafarin.mata.common.exception;

/**
 * Created by Asus on 11/5/2017.
 */
public abstract class MataBusinessException extends RuntimeException {

    public MataBusinessException() {
    }

    public MataBusinessException(String message) {
        super(message);
    }

    public MataBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public MataBusinessException(Throwable cause) {
        super(cause);
    }
}
