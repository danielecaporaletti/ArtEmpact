package com.example.s3Demo.S3.exceptions;

public class DB_FileNotFoundException extends SpringBootFileUploadException {
    public DB_FileNotFoundException(String message) {
        super(message);
    }
}
