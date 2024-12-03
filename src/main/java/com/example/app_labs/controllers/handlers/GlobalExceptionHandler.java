package com.example.app_labs.controllers.handlers;

import com.example.app_labs.services.exceptions.BadDataException;
import com.example.app_labs.services.exceptions.NonexistentRecordException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NonexistentRecordException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(NonexistentRecordException ex) {
        ApiError apiError = new ApiError(LocalDateTime.now(), ex.getMessage(),
                HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadDataException.class)
    public ResponseEntity<ApiError> handleBadDataException(BadDataException ex) {
        ApiError apiError = new ApiError(LocalDateTime.now(), ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    // The default message is long. This method extracts the messages associated with validation failures, creating
    // a new string. This string comprises each message generated when data binding fails.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleBadDataSentInRequest(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String errorMessage = fieldErrors.stream()
                .map(fieldError ->
                        String.format("Field '%s': %s", fieldError.getField(),
                                fieldError.getDefaultMessage()))
                .collect(Collectors.joining(" | "));

        ApiError apiError = new ApiError(LocalDateTime.now(), errorMessage, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    // What if a request tries to add the same PK? Data integrity error occurs in the repository layer but is also handled here
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        ApiError apiError = new ApiError(LocalDateTime.now(),
                "The request would have created a conflict.", HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }
}