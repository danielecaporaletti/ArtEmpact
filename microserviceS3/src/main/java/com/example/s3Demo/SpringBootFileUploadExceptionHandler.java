package com.example.s3Demo;

import com.example.s3Demo.S3.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class SpringBootFileUploadExceptionHandler extends ResponseEntityExceptionHandler {

    /*
     * DB exceptions
     */

    @ExceptionHandler(DB_InvalidUploadException.class)
    public ResponseEntity<Object> handleDBInvalidUploadException(DB_InvalidUploadException ex, WebRequest request) {
        // Get the request details
        String requestUri = request.getDescription(true);
        HttpMethod httpMethod = ((ServletWebRequest) request).getHttpMethod();

        // Error log with additional details
        log.error("{} caught for requestUri: {}, HttpMethod: {}", ex.getClass().getSimpleName(), requestUri, httpMethod, ex);

        //Construct the ApiError object with the error details
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "Database operation error.");
        // Return the ResponseEntity with the ApiError and the appropriate HTTP status
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }


    @ExceptionHandler(DB_FileNotFoundException.class)
    public ResponseEntity<Object> handleDBFileNotFoundException(DB_FileNotFoundException ex, WebRequest request) {
        // Get the request details
        String requestUri = request.getDescription(true);
        HttpMethod httpMethod = ((ServletWebRequest) request).getHttpMethod();

        // Error log with additional details
        log.error("{} caught for requestUri: {}, HttpMethod: {}", ex.getClass().getSimpleName(), requestUri, httpMethod, ex);

        //Construct the ApiError object with the error details
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), "File not found in the database");
        // Return the ResponseEntity with the ApiError and the appropriate HTTP status
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(DB_ColumnNotFoundException.class)
    public ResponseEntity<Object> handleDBColumnNotFoundException(DB_ColumnNotFoundException ex, WebRequest request) {
        // Get the request details
        String requestUri = request.getDescription(true);
        HttpMethod httpMethod = ((ServletWebRequest) request).getHttpMethod();

        // Error log with additional details
        log.error("{} caught for requestUri: {}, HttpMethod: {}", ex.getClass().getSimpleName(), requestUri, httpMethod, ex);

        //Construct the ApiError object with the error details
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), "Invalid file type specified");
        // Return the ResponseEntity with the ApiError and the appropriate HTTP status
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(DB_InvalidFileTypeException.class)
    public ResponseEntity<Object> handleDBInvalidFileTypeException(DB_InvalidFileTypeException ex, WebRequest request) {
        // Get the request details
        String requestUri = request.getDescription(true);
        HttpMethod httpMethod = ((ServletWebRequest) request).getHttpMethod();

        // Error log with additional details
        log.error("{} caught for requestUri: {}, HttpMethod: {}", ex.getClass().getSimpleName(), requestUri, httpMethod, ex);

        //Construct the ApiError object with the error details
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), "Invalid file type specified");
        // Return the ResponseEntity with the ApiError and the appropriate HTTP status
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<Object> handleDatabaseException(DatabaseException ex, WebRequest request) {
        // Get the request details
        String requestUri = request.getDescription(true);
        HttpMethod httpMethod = ((ServletWebRequest) request).getHttpMethod();

        // Error log with additional details
        log.error("{} caught for requestUri: {}, HttpMethod: {}", ex.getClass().getSimpleName(), requestUri, httpMethod, ex);

        //Construct the ApiError object with the error details
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), "Invalid file type specified");
        // Return the ResponseEntity with the ApiError and the appropriate HTTP status
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }


    /*
     * AWS S3 exceptions
     */

    @ExceptionHandler(S3StorageException.class)
    protected ResponseEntity<Object> handleS3FileStorageException(S3StorageException ex, WebRequest request) {
        // Get the request details
        String requestUri = request.getDescription(true);
        HttpMethod httpMethod = ((ServletWebRequest) request).getHttpMethod();

        // Error log with additional details
        log.error("{} caught for requestUri: {}, HttpMethod: {}", ex.getClass().getSimpleName(), requestUri, httpMethod, ex);

        //Construct the ApiError object with the error details
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "An error occurred while storing the file.");
        // Return the ResponseEntity with the ApiError and the appropriate HTTP status
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(S3_FileUploadException.class)
    protected ResponseEntity<Object> handleS3FileUploadException(S3_FileUploadException ex, WebRequest request) {
        // Get the request details
        String requestUri = request.getDescription(true);
        HttpMethod httpMethod = ((ServletWebRequest) request).getHttpMethod();

        // Error log with additional details
        log.error("{} caught for requestUri: {}, HttpMethod: {}", ex.getClass().getSimpleName(), requestUri, httpMethod, ex);

        //Construct the ApiError object with the error details
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), "An error occurred during the file upload process.");
        // Return the ResponseEntity with the ApiError and the appropriate HTTP status
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    /*
     * Controller exceptions
     */

    @ExceptionHandler(FileEmptyException.class)
    protected ResponseEntity<Object> handleFileEmptyException(FileEmptyException ex, WebRequest request) {
        // Get the request details
        String requestUri = request.getDescription(true);
        HttpMethod httpMethod = ((ServletWebRequest) request).getHttpMethod();

        // Error log with additional details
        log.error("{} caught for requestUri: {}, HttpMethod: {}", ex.getClass().getSimpleName(), requestUri, httpMethod, ex);

        //Construct the ApiError object with the error details
        ApiError apiError = new ApiError(HttpStatus.NO_CONTENT, ex.getLocalizedMessage(), "The file is not correct.");
        // Return the ResponseEntity with the ApiError and the appropriate HTTP status
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(FileExtensionException.class)
    protected ResponseEntity<Object> handleFileExtansionException(FileExtensionException ex, WebRequest request) {
        // Get the request details
        String requestUri = request.getDescription(true);
        HttpMethod httpMethod = ((ServletWebRequest) request).getHttpMethod();

        // Error log with additional details
        log.error("{} caught for requestUri: {}, HttpMethod: {}", ex.getClass().getSimpleName(), requestUri, httpMethod, ex);

        //Construct the ApiError object with the error details
        ApiError apiError = new ApiError(HttpStatus.NO_CONTENT, ex.getLocalizedMessage(), "The file is not correct.");
        // Return the ResponseEntity with the ApiError and the appropriate HTTP status
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(RequestException.class)
    protected ResponseEntity<Object> handleNoPositionException(RequestException ex, WebRequest request) {
        // Get the request details
        String requestUri = request.getDescription(true);
        HttpMethod httpMethod = ((ServletWebRequest) request).getHttpMethod();

        // Error log with additional details
        log.error("{} caught for requestUri: {}, HttpMethod: {}", ex.getClass().getSimpleName(), requestUri, httpMethod, ex);

        //Construct the ApiError object with the error details
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), "Lack of information.");
        // Return the ResponseEntity with the ApiError and the appropriate HTTP status
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(FileUploadException.class)
    protected ResponseEntity<Object> handleFileUploadException(FileUploadException ex, WebRequest request) {
        // Get the request details
        String requestUri = request.getDescription(true);
        HttpMethod httpMethod = ((ServletWebRequest) request).getHttpMethod();

        // Error log with additional details
        log.error("{} caught for requestUri: {}, HttpMethod: {}", ex.getClass().getSimpleName(), requestUri, httpMethod, ex);

        //Construct the ApiError object with the error details
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "Something wrong internal.");
        // Return the ResponseEntity with the ApiError and the appropriate HTTP status
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(InvalidFileException.class)
    protected ResponseEntity<Object> handleInvalidFileException(InvalidFileException ex, WebRequest request) {
        // Get the request details
        String requestUri = request.getDescription(true);
        HttpMethod httpMethod = ((ServletWebRequest) request).getHttpMethod();

        // Error log with additional details
        log.error("{} caught for requestUri: {}, HttpMethod: {}", ex.getClass().getSimpleName(), requestUri, httpMethod, ex);

        //Construct the ApiError object with the error details
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "Something wrong internal.");
        // Return the ResponseEntity with the ApiError and the appropriate HTTP status
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
