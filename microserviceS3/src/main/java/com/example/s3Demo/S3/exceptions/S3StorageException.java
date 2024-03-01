package com.example.s3Demo.S3.exceptions;

public class S3StorageException extends SpringBootFileUploadException {
    public S3StorageException(String message) {
        super(message);
    }
}
