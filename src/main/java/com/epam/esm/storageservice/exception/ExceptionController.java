package com.epam.esm.storageservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<ApiExceptionResponse> getConstraintViolationException(ConstraintViolationException exception) {
        return new ResponseEntity<>(getResponseEntity(exception), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {S3BucketNotExistException.class})
    public ResponseEntity<ApiExceptionResponse> getS3BucketNotExistData(S3BucketNotExistException exception) {
        return new ResponseEntity<>(getResponseEntity(exception), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<ApiExceptionResponse> getHttpMessageNotReadableExceptionData(HttpMessageNotReadableException exception) {
        return new ResponseEntity<>(getResponseEntity(exception), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ApiExceptionResponse> getData(RuntimeException exception) {
        return new ResponseEntity<>(getResponseEntity(exception), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    private ApiExceptionResponse getResponseEntity(Exception exception) {
        return ApiExceptionResponse.builder()
                .exception(exception.getClass().getName())
                .message(exception.getMessage())
                .build();
    }
}
