package com.example.s3Demo.S3.exceptions;

public class DatabaseException extends SpringBootFileUploadException {
    public DatabaseException(String message) {
        super(message);
    }
}
