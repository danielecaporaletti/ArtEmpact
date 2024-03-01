package com.example.s3Demo.S3.exceptions;

public class S3_FileUploadException extends SpringBootFileUploadException {
    public S3_FileUploadException(String message) {
        super(message);
    }
}
