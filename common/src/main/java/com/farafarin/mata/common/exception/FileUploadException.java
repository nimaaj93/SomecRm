package com.farafarin.mata.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Asus on 5/19/2018.
 */

@ResponseStatus(code = HttpStatus.BAD_REQUEST,reason = "file.upload.failed")
public class FileUploadException extends RuntimeException{
    public FileUploadException(String message){
        super(message);
    }

    public FileUploadException(Throwable cause){
        super(cause);
    }
}
