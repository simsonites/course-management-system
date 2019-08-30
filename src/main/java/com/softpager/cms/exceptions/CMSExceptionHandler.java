package com.softpager.cms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CMSExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<CMSErrorResponse> handleException(UsernameNotFoundException userNotFound){
        CMSErrorResponse cmsError = new CMSErrorResponse();
        cmsError.setStatus(HttpStatus.NOT_FOUND.value());
        cmsError.setMessage(userNotFound.getMessage());
        cmsError.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(cmsError, HttpStatus.NOT_FOUND);
    }

  @ExceptionHandler
    public ResponseEntity<CMSErrorResponse> handleException(UserAlreadyExistException userExist){
        CMSErrorResponse cmsError = new CMSErrorResponse();
        cmsError.setStatus(HttpStatus.FOUND.value());
        cmsError.setMessage(userExist.getMessage());
        cmsError.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(cmsError, HttpStatus.NOT_FOUND);
    }
/*
    @ExceptionHandler
    public ResponseEntity<CMSErrorResponse> handleException(Exception exc) {
        CMSErrorResponse error = new CMSErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }*/


}
