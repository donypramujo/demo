package com.jaxi.exception;

public class MerchantCategoryNotFoundException extends RuntimeException {

    public MerchantCategoryNotFoundException() {
    }

    public MerchantCategoryNotFoundException(String message) {
        super(message);
    }

    public MerchantCategoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MerchantCategoryNotFoundException(Throwable cause) {
        super(cause);
    }

    public MerchantCategoryNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
