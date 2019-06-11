package com.softpager.cms.exceptions;

public class StudentAlreadyExistException extends RuntimeException {

    public StudentAlreadyExistException() {
        super();
    }

    public StudentAlreadyExistException(String message) {
        super(message);
    }

    public StudentAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentAlreadyExistException(Throwable cause) {
        super(cause);
    }

    protected StudentAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
