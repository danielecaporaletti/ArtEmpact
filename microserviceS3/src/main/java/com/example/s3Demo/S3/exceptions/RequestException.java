package com.example.s3Demo.S3.exceptions;

public class RequestException extends SpringBootFileUploadException {
    public RequestException(String message) {
        super(message);
    }
}
