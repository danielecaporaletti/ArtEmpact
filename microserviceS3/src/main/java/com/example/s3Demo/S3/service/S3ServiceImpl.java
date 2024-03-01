package com.example.s3Demo.S3.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.HttpMethod;
import com.example.s3Demo.S3.exceptions.S3StorageException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.net.URL;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {
    @Value("${aws.bucket.name}")
    private String bucketName;

    private final AmazonS3 s3Client;


    @Override
    public URL uploadFileS3(MultipartFile multipartFile, String fileName) throws S3StorageException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());

            PutObjectRequest request = new PutObjectRequest(bucketName, fileName, file);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("plain/" + FilenameUtils.getExtension(multipartFile.getOriginalFilename()));
            metadata.addUserMetadata("Title", "File Upload - " + fileName);
            metadata.setContentLength(file.length());
            request.setMetadata(metadata);
            s3Client.putObject(request);

            log.info("File uploaded successfully to S3. FileName: {}", fileName);
        } catch (IOException | AmazonServiceException e) {
            log.error("Error uploading file to S3", e);
            throw new S3StorageException("Error uploading file to S3: " + e.getMessage());
        } finally {
            if (!file.delete()) log.warn("Failed to delete temporary file: {}", file.getPath());
        }

        return generatePresignedUrl(fileName);
    }

    /**
     * Deletes a file from an Amazon S3 bucket.
     * This method attempts to delete a specified file from the configured S3 bucket using the Amazon S3 client.
     * If the file is successfully deleted, the method completes without exception.
     * If an error occurs during the deletion process, such as the file not being found or issues with AWS permissions,
     * an {@link S3StorageException} is thrown, detailing the error.
     *
     * @param fileName The name of the file to be deleted from the S3 bucket.
     * @throws S3StorageException If an error occurs during the file deletion process. This custom exception wraps
     * the underlying {@link AmazonServiceException} to provide a more context-specific error message.
     */
    @Override
    public void deleteFileS3(String fileName) throws S3StorageException {
        try {
            s3Client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
            log.info("File {} deleted successfully from bucket {}", fileName, bucketName);
        } catch (AmazonServiceException e) {
            log.error("Error occurred while deleting file {} from bucket {}: {}", fileName, bucketName, e.getMessage());
            throw new S3StorageException("Error deleting file " + fileName + " from bucket " + bucketName + ": " + e.getMessage());
        }
    }


    /**
     * Generates a presigned URL for accessing a file stored in Amazon S3. The URL is valid for a limited time, specified by the expiration parameter.
     *
     * @param fileName The name (or key) of the file in the S3 bucket for which the presigned URL is to be generated.
     * @return A {@link URL} object representing the presigned URL which can be used to access the file.
     * @throws S3StorageException If an error occurs while generating the presigned URL.
     */
    public URL generatePresignedUrl(String fileName) throws S3StorageException {
        Date expiration = new Date(System.currentTimeMillis() + 1000 * 60 * 60); // 1 hour from now
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, fileName)
                .withMethod(HttpMethod.GET)
                .withExpiration(expiration);

        try {
            return s3Client.generatePresignedUrl(generatePresignedUrlRequest);
        } catch (AmazonServiceException e) {
            log.error("Error generating presigned URL", e);
            throw new S3StorageException("Error generating presigned URL: " + e.getMessage());
        }
    }




}