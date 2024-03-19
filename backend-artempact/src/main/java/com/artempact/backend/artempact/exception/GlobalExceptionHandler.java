package com.artempact.backend.artempact.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Metodo per catturare le eccezioni di @Valid di Jakarta e ritornare un ResponseEntity
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        ex.getBindingResult().getGlobalErrors().forEach(error ->
                errors.put(error.getObjectName(), error.getDefaultMessage()));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Metodi per catturare le eccezioni di jackson e ritornare un ResponseEntity
    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<Object> handleJsonParseException(JsonParseException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, "Malformed JSON request", ex.getOriginalMessage());
    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<Object> handleJsonMappingException(JsonMappingException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, "JSON mapping error", ex.getOriginalMessage());
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, "Invalid format", ex.getOriginalMessage());
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<Object> handleJsonProcessingException(JsonProcessingException ex) {
        // Questo catch-all per JsonProcessingException cattura eccezioni non gestite specificamente dagli altri handler
        return buildResponseEntity(HttpStatus.BAD_REQUEST, "JSON processing error", ex.getOriginalMessage());
    }

    // Metodo helper per costruire il ResponseEntity
    private ResponseEntity<Object> buildResponseEntity(HttpStatus status, String error, String message) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", error);
        errorDetails.put("message", message);
        return new ResponseEntity<>(errorDetails, status);
    }

}
