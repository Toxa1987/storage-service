package com.epam.esm.storageservice.exception;

public class S3BucketNotExistException extends RuntimeException {
    public S3BucketNotExistException(String message) {
        super(message);
    }
}
