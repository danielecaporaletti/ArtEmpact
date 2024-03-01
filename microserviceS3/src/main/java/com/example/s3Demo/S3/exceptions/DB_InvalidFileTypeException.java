package com.example.s3Demo.S3.exceptions;

public class DB_InvalidFileTypeException extends SpringBootFileUploadException {
    public DB_InvalidFileTypeException(String message) {
        super(message);
    }
}
