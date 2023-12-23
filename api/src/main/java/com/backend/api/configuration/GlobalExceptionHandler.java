package com.backend.api.configuration;


import com.backend.api.entity.ErrorInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.security.auth.message.AuthException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleMethodNotSupported(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new ErrorInfo(ex, HttpStatus.METHOD_NOT_ALLOWED, "HTTP request method not supported for this operation."));
    }

    @ExceptionHandler({
            AccessDeniedException.class,
            AuthorizationServiceException.class})
    public ResponseEntity<?> handleAuthorizationServiceException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorInfo(ex, HttpStatus.UNAUTHORIZED, "Authorization Error: " + ex.getMessage()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorInfo(ex, HttpStatus.UNAUTHORIZED, "Authentication Error: " + ex.getMessage()));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> handleIOException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorInfo(ex, HttpStatus.BAD_REQUEST, "IO Error: " + ex.getMessage()));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNullPointerException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorInfo(ex, HttpStatus.NOT_FOUND, "Result not found from query."));
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<?> handleSqlException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorInfo(ex, HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorInfo( ex, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorInfo(ex, HttpStatus.INTERNAL_SERVER_ERROR, "Runtime Error: " + ex.getMessage()));
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<?> handleAuthException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorInfo(ex, HttpStatus.UNAUTHORIZED, "Authorization Error: " + ex.getMessage()));
    }

    @ExceptionHandler({
            InvalidFormatException.class,
            ServletRequestBindingException.class,
            MethodArgumentTypeMismatchException.class,
            MissingPathVariableException.class,
            TypeMismatchException.class,
            InvalidFormatException.class,
            MethodArgumentNotValidException.class,
            ConstraintViolationException.class,
            BindException.class
    })
    public ResponseEntity<?> handleRequestFieldValidationException(Exception ex) {
        StringBuilder errorMessage= new StringBuilder();
        if (ex instanceof BindingResult bindingResult) {
            if (bindingResult.hasFieldErrors()) {
                for (FieldError error : bindingResult.getFieldErrors()) {
                    errorMessage.append(Objects.requireNonNull(Objects.requireNonNull(error.getDefaultMessage()).endsWith(".")?
                            error.getDefaultMessage().concat(" ")
                            : error.getDefaultMessage().concat(". ")));
                }
            }
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorInfo(ex, HttpStatus.BAD_REQUEST, errorMessage.toString()));
        } if (ex instanceof ConstraintViolationException) {
            ((ConstraintViolationException) ex).getConstraintViolations().forEach(
                    error -> errorMessage.append(error.getMessage()).append(" "));
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new ErrorInfo(ex, HttpStatus.UNPROCESSABLE_ENTITY, errorMessage.toString()));
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorInfo(ex, HttpStatus.BAD_REQUEST, "Validation error occurred for requested input parameters."));
        }
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<?> handleMissingServletRequestPartException(MissingServletRequestPartException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorInfo(ex, HttpStatus.BAD_REQUEST, "Required multipart params or files are missing."));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorInfo(ex, HttpStatus.BAD_REQUEST, String.format("「%s」が必要です。", ex.getParameterName())));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorInfo(ex, HttpStatus.BAD_REQUEST, Objects.requireNonNull(ex.getMessage())));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<?> handleMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
        return ResponseEntity
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(new ErrorInfo(ex, HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Supportable media file required."));
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorInfo(ex, HttpStatus.BAD_REQUEST, Objects.requireNonNull(ex.getMessage())));
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<Object> handleJsonProcessingException(JsonProcessingException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorInfo(ex, HttpStatus.BAD_REQUEST, "Error occurred while processing json."));
    }

}
