package com.farafarin.mata.admin.front.advice;

import com.farafarin.mata.common.logback.annotation.Log;
import com.farafarin.mata.common.model.ResponseError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by Asus on 11/4/2017.
 */
@ControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {
    private static Logger LOG = LoggerFactory.getLogger(GlobalControllerAdvice.class);


    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        ResponseError responseError;
        Class exClass = ex.getClass();
        if (exClass.isAnnotationPresent(ResponseStatus.class)) {
            ResponseStatus annotation = (ResponseStatus) exClass.getAnnotation(ResponseStatus.class);
            Integer code = annotation.code().value();
            String reason = annotation.reason();
            responseError = new ResponseError(code,reason);
        } else {
            responseError = new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"unknown.exception");
        }
        LOG.info(ex.getMessage(),ex);
        return handleExceptionInternal(ex,responseError ,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = { AccessDeniedException.class })
    protected ResponseEntity<Object> handleAccessDenied(RuntimeException ex, WebRequest request) {
        ResponseError responseError = new ResponseError();
        responseError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseError.setReason("access.denied");
        LOG.info(ex.getMessage(),ex);
        return handleExceptionInternal(ex,responseError ,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
