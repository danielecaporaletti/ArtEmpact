package com.example.s3Demo.S3.web.controller;

import com.example.s3Demo.S3.exceptions.*;
import com.example.s3Demo.S3.service.S3Service;
import com.example.s3Demo.S3.web.APIResponse;
import com.example.s3Demo.repositorySQL.DBService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class S3ControllerService {

    private final FileValidator fileValidator;
    private final DBService dbService;
    private final S3Service s3Service;

    public S3ControllerService(FileValidator fileValidator, DBService dbService, S3Service s3Service) {
        this.fileValidator = fileValidator;
        this.dbService = dbService;
        this.s3Service = s3Service;
    }

    public APIResponse uploadFile(JwtAuthenticationToken auth, MultipartFile file, String type, String typeOfCard, String cardId) throws InvalidFileException, DB_ColumnNotFoundException, RequestException, DB_FileNotFoundException, S3StorageException, S3_FileUploadException {

        ValidationContext valid = new ValidationContext(typeOfCard, cardId, type, auth, dbService); // Create context object

        // validate "file" field
        fileValidator.validate(file, valid.getFileType());
        String fileName = generateFileName(valid.getFileType(), file);

        // Return the first position of empty fileType column. For example return "1" if "photo1" is available.
        // Throw exception if there isn't free space.
        String firstFreePosition = dbService.findFirstEmptyPositionInTable(valid.getContextId(), valid.getTableName(), valid.getFileType(), true);

        dbService.updateFileDB(valid.getContextId(), valid.getTableName(), valid.getFileType() + firstFreePosition, fileName);

        URL url = s3Service.uploadFileS3(file, fileName);

        return new APIResponse(valid.fileType + " uploaded successfully in " + valid.getFileType() + firstFreePosition + " field.", url.toString(), fileName,true,201);
    }

    public APIResponse updateFile(JwtAuthenticationToken auth, MultipartFile file, String type, String fileNameToChange, String typeOfCard, String cardId) throws InvalidFileException, S3StorageException, DB_ColumnNotFoundException, RequestException, DB_FileNotFoundException, S3_FileUploadException {

        ValidationContext valid = new ValidationContext(typeOfCard, cardId, type, auth, dbService);

        // validate "file" field
        fileValidator.validate(file, valid.getFileType());

        String fileName = generateFileName(valid.getFileType(), file);

        // Return position of the file to change
        String positionFileToChange = dbService.checkIfFileExist(valid.getContextId(), valid.getTableName(), valid.getFileType(), fileNameToChange);

        String columnNameInDB = valid.getFileType() + positionFileToChange;
        s3Service.deleteFileS3(fileNameToChange);
        dbService.updateFileDB(valid.getContextId(), valid.getTableName(), columnNameInDB, fileName);
        URL url = s3Service.uploadFileS3(file, fileName);
        return new APIResponse(valid.fileType + " uploaded successfully in " + columnNameInDB + " field.", url.toString(), fileName,true,202);
    }

    public APIResponse deleteFile(JwtAuthenticationToken auth, String fileName, String typeOfCard, String cardId) throws RequestException, DB_ColumnNotFoundException, DB_FileNotFoundException, S3StorageException {

        // Extract fileType from value
        String type = fileName.substring(0, fileName.indexOf("_"));

        ValidationContext valid = new ValidationContext(typeOfCard, cardId, type, auth, dbService);

        // Find if the file exist and find the position of the fileName
        String position = dbService.checkIfFileExist(valid.contextId, valid.tableName, valid.fileType, fileName);

        String columnNameInDB = valid.fileType + position;

        // Delete from database
        dbService.deleteFile(valid.contextId, valid.tableName, columnNameInDB, fileName);
        // Delete from S3
        s3Service.deleteFileS3(fileName);

        return new APIResponse(valid.fileType + " deleted successfully in " + valid.tableName + " table and in S3", fileName, null,true,200);
    }

    public List<String> getSignedUrls(JwtAuthenticationToken auth, List<String> fileNames) throws S3StorageException {
        List<String> signedUrls = new ArrayList<>();

        for (String fileName : fileNames) {
            URL url = s3Service.generatePresignedUrl(fileName); // Potrebbe lanciare S3_FileStorageException
            signedUrls.add(url.toString()); // Converti l'URL in stringa e aggiungilo alla lista
        }

        return signedUrls;
    }

    /**
     * Generates a unique file name based on the user ID, position, and the current timestamp.
     *
     * @param fileType The type of the file, such as "Photo" or "Video".
     * @param multipartFile The file from which to extract the extension.
     * @return A string representing the generated unique file name.
     */
    private String generateFileName(String fileType, MultipartFile multipartFile) {
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        String timestamp = String.valueOf(System.currentTimeMillis());
        return fileType  + "_" + timestamp + "." + extension;
    }
}
