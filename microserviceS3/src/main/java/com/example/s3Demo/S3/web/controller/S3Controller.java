package com.example.s3Demo.S3.web.controller;

import com.example.s3Demo.S3.exceptions.*;
import com.example.s3Demo.S3.web.APIResponse;
import com.example.s3Demo.cache.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Controller for handling file operations with Amazon S3.
 * Provides endpoints for uploading and managing files stored in an S3 bucket.
 */
@RestController
@RequestMapping("/api/S3/v0")
@Validated
public class S3Controller {

    private final S3ControllerService s3ControllerService;

    public S3Controller(S3ControllerService s3ControllerService) {
        this.s3ControllerService = s3ControllerService;
    }

    @Autowired
    private CacheService cacheService;

    /**
     * Handles the upload of a new file to Amazon S3.
     *
     * @param auth             A JwtAuthenticationToken representing the authenticated user.
     * @param file             The MultipartFile object representing the file to be uploaded.
     * @param type             The type of file being uploaded ("photo", "premiumPhoto", etc.).
     * @param typeOfCard       An optional parameter specifying the type of card associated with the file.
     * @param cardId           An optional parameter specifying the card ID associated with the file.
     * @return A ResponseEntity containing the APIResponse object (with filename and URL) and a CREATED status.
     * @throws DB_ColumnNotFoundException  If a required database column is not found.
     * @throws FileEmptyException          If the uploaded file is empty.
     * @throws DB_FileNotFoundException    If the file cannot be located in the database.
     * @throws RequestException            If there is an issue with the HTTP request.
     * @throws InvalidFileException        If the provided file is invalid.
     * @throws S3StorageException          If there are errors during S3 interaction.
     * @throws S3_FileUploadException      If the file upload to S3 fails.
     */
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(
            JwtAuthenticationToken auth,
            @RequestParam("file") MultipartFile file,
            @RequestParam("type") String type, // "photo", "premiumPhoto", "document", "video" ecc.
            @RequestParam(value = "typeOfCard", required = false) String typeOfCard,
            @RequestParam(value = "cardId", required = false) String cardId
    ) throws DB_ColumnNotFoundException, FileEmptyException, DB_FileNotFoundException, RequestException, InvalidFileException, S3StorageException, S3_FileUploadException {
        APIResponse apiResponse = s3ControllerService.uploadFile(auth, file, type, typeOfCard, cardId);
        String fileName = apiResponse.getFileName();
        String url = apiResponse.getUrl();
        cacheService.put(fileName, url);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    /**
     * Handles the update of an existing file on Amazon S3.
     *
     * @param auth             A JwtAuthenticationToken representing the authenticated user.
     * @param file             The MultipartFile object representing the file to be updated.
     * @param type             The type of file being updated ("photo", "premiumPhoto", etc.).
     * @param fileNameToChange The name of the file to be changed.
     * @param typeOfCard       An optional parameter specifying the type of card associated with the file.
     * @param cardId           An optional parameter specifying the card ID associated with the file.
     * @return A ResponseEntity containing the APIResponse object (with filename and URL) and an ACCEPTED status.
     * @throws DB_ColumnNotFoundException  If a required database column is not found.
     * @throws DB_FileNotFoundException    If the file cannot be found in the database.
     * @throws RequestException            If there is an issue with the HTTP request.
     * @throws InvalidFileException        If the provided file is invalid.
     * @throws S3StorageException          If there are errors during S3 interaction.
     * @throws S3_FileUploadException      If the file upload to S3 fails.
     */
    @PutMapping("/update")
    public ResponseEntity<?> updateFile(
            JwtAuthenticationToken auth,
            @RequestParam("file") MultipartFile file,
            @RequestParam("type") String type, // "photo", "premiumPhoto", "document", "video" ecc.
            @RequestParam(value = "fileName") String fileNameToChange,
            @RequestParam(value = "typeOfCard", required = false) String typeOfCard,
            @RequestParam(value = "cardId", required = false) String cardId
    ) throws DB_ColumnNotFoundException, DB_FileNotFoundException, RequestException, InvalidFileException, S3StorageException, S3_FileUploadException {
        APIResponse apiResponse = s3ControllerService.updateFile(auth, file, type, fileNameToChange, typeOfCard, cardId);
        String fileName = apiResponse.getFileName();
        String url = apiResponse.getUrl();
        cacheService.remove(fileNameToChange);
        cacheService.put(fileName, url);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
    }

    /**
     * Handles the deletion of a file from Amazon S3.
     *
     * @param auth             A JwtAuthenticationToken representing the authenticated user.
     * @param fileName         The name of the file to be deleted.
     * @param typeOfCard       An optional parameter specifying the type of card associated with the file.
     * @param cardId           An optional parameter specifying the card ID associated with the file.
     * @return A ResponseEntity containing the APIResponse object and an OK status.
     * @throws DB_ColumnNotFoundException  If a required database column is not found.
     * @throws DB_FileNotFoundException    If the file cannot be found in the database.
     * @throws RequestException            If there is an issue with the HTTP request.
     * @throws S3StorageException          If there are errors during S3 interaction.
     */
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFile(
            JwtAuthenticationToken auth,
            @RequestParam("fileName") String fileName,
            @RequestParam(value = "typeOfCard", required = false) String typeOfCard,
            @RequestParam(value = "cardId", required = false) String cardId
    ) throws DB_ColumnNotFoundException, DB_FileNotFoundException, RequestException, S3StorageException {
        APIResponse apiResponse = s3ControllerService.deleteFile(auth, fileName, typeOfCard, cardId);
        cacheService.remove(fileName);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


    @GetMapping("/getSignedUrls")
    public ResponseEntity<List<String>> getSignedUrls(
            JwtAuthenticationToken auth,
            @RequestParam(value = "fileName") List<String> fileNames
    ) throws S3StorageException {
        List<String> signedUrls = new ArrayList<>();
        for (String fileName : fileNames) {
            // Controlla se l'URL è presente in cache
            String cachedUrl = (String) cacheService.get(fileName);
            if (cachedUrl != null) {
                // Se presente in cache, usa l'URL dalla cache
                signedUrls.add(cachedUrl);
            } else {
                // Se non presente in cache, genera un nuovo URL pre-firmato
                List<String> singleFileNameList = Collections.singletonList(fileName);
                List<String> newSignedUrls = s3ControllerService.getSignedUrls(auth, singleFileNameList);
                if (!newSignedUrls.isEmpty()) {
                    String newSignedUrl = newSignedUrls.get(0);
                    signedUrls.add(newSignedUrl);
                    // Salva il nuovo URL in cache
                    cacheService.put(fileName, newSignedUrl);
                }
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(signedUrls);
    }

}

