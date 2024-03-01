package com.example.s3Demo.S3.exceptions;

public class InvalidFileException extends SpringBootFileUploadException{
    public InvalidFileException(String message) {
        super(message);
    }
}
