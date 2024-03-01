package com.example.s3Demo.S3.exceptions;

public class FileExtensionException extends SpringBootFileUploadException {
    public FileExtensionException(String message) {
        super(message);
    }
}
