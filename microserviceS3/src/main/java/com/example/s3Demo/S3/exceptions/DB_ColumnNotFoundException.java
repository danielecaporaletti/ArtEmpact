package com.example.s3Demo.S3.exceptions;

public class DB_ColumnNotFoundException extends SpringBootFileUploadException {
    public DB_ColumnNotFoundException(String message) {
        super(message);
    }

}
