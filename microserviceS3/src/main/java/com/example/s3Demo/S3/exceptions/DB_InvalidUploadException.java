package com.example.s3Demo.S3.exceptions;

public class DB_InvalidUploadException extends SpringBootFileUploadException {
    public DB_InvalidUploadException(String message) {
        super(message);
    }
}
