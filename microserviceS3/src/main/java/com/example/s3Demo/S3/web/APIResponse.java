package com.example.s3Demo.S3.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class APIResponse {
    private String message;
    private String fileName;
    private String url;
    private boolean isSuccessful;
    private int statusCode;
}
