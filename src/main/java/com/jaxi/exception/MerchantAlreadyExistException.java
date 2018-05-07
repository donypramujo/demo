package com.jaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class MerchantAlreadyExistException extends RuntimeException{
    public MerchantAlreadyExistException() {
    }

    public MerchantAlreadyExistException(String message) {
        super(message);
    }

    public MerchantAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public MerchantAlreadyExistException(Throwable cause) {
        super(cause);
    }

    public MerchantAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
