package com.farafarin.mata.admin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;

/**
 * Created by Asus on 11/4/2017.
 */
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR,reason = "Failure in user creation")
public class UserCreationException extends RuntimeException {
}
