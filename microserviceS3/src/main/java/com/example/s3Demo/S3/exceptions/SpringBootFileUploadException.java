package com.example.s3Demo.S3.exceptions;

/*
  Project’s base exception class, from which all other exception classes will extend.
 */
public class SpringBootFileUploadException extends Exception{
    public SpringBootFileUploadException(String message) {
        super(message);
    }
}
