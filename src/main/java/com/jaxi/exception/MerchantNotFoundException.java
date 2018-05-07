package com.jaxi.exception;

public class MerchantNotFoundException extends RuntimeException {

    public MerchantNotFoundException() {
    }

    public MerchantNotFoundException(String message) {
        super(message);
    }

    public MerchantNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MerchantNotFoundException(Throwable cause) {
        super(cause);
    }

    public MerchantNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
