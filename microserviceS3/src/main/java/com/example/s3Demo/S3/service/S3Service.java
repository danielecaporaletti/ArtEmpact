package com.example.s3Demo.S3.service;

import com.example.s3Demo.S3.exceptions.S3StorageException;
import com.example.s3Demo.S3.exceptions.S3_FileUploadException;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

/*
  Following the strategy design pattern, create a FileServiceImpl.java class that implements FileService.java
  under the service package of your project. The Strategy design pattern allows us to write loosely coupled code
  that is not bound to AWS S3.

  If we decide to use Dropbox in the future instead of AWS S3,
  all we need to do is create a new class that implements FileService.java which will contain Dropbox-specific
  code for uploading files and downloading files.
 */
public interface S3Service {
    URL uploadFileS3(MultipartFile multipartFile, String fileName) throws S3_FileUploadException, S3StorageException;
    void deleteFileS3(String fileName) throws S3StorageException;
    URL generatePresignedUrl(String fileName) throws S3StorageException;

}
