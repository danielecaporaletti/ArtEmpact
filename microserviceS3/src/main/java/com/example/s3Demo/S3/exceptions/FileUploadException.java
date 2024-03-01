package com.example.s3Demo.S3.exceptions;

public class FileUploadException extends SpringBootFileUploadException {

    public FileUploadException(String message) {
        super(message);
    }
}
