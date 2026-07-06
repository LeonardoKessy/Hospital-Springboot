package com.lk.hospitalspringboot.modules.shared.infrastructure.adapters.in.rest.advice;

import com.lk.hospitalspringboot.modules.shared.domain.exceptions.*;
import com.lk.hospitalspringboot.modules.shared.infrastructure.adapters.in.rest.responses.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class GlobalExceptionControllerAdvice {

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ErrorResponse> handleBusinessRuleException(
            BusinessRuleException e,
            HttpServletRequest request
    ) {
        var error = ErrorResponse.of(
                HttpStatus.UNPROCESSABLE_CONTENT,
                e.getCode(),
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(error);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException e,
            HttpServletRequest request
    ) {
        var error = ErrorResponse.of(
                HttpStatus.NOT_FOUND,
                e.getCode(),
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<ErrorResponse> handleInputValidationException(
            InputValidationException e,
            HttpServletRequest request
    ) {
        var error = ErrorResponse.of(
                HttpStatus.BAD_REQUEST,
                e.getCode(),
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(InputValidationBundleException.class)
    public ResponseEntity<ErrorResponse> handleInputValidationBundleException(
            InputValidationBundleException e,
            HttpServletRequest request
    ) {
        var error = ErrorResponse.bundle(
                HttpStatus.BAD_REQUEST,
                e.getCode(),
                e.getMessage(),
                request.getRequestURI(),
                e.getErrors()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(
            DataIntegrityViolationException e,
            HttpServletRequest request
    ) {
        // Generic
        HttpStatus status = HttpStatus.UNPROCESSABLE_CONTENT;
        String message = "The request was rejected due to a database integrity violation";
        String code = "DATABASE_INTEGRITY_VIOLATION";

        String rootMessage = e.getMostSpecificCause().getMessage();

        if (rootMessage != null) {
            if (rootMessage.contains("uq_")) {
                message = "The request was rejected because it violates a unique data constraint";
                code  = "UNIQUE_CONSTRAINT_VIOLATION";
            }
            else if (rootMessage.contains("fk_")) {
                status = HttpStatus.BAD_REQUEST;
                message = "The request references a related resource that does not exist";
                code = "FOREIGN_KEY_VIOLATION";
            }
        }

        var error = ErrorResponse.of(
                status,
                code,
                message,
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(InvalidTypeConversionException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTypeConversionException(
            InvalidTypeConversionException e,
            HttpServletRequest request
    ) {
        var error = ErrorResponse.of(
                HttpStatus.BAD_REQUEST,
                e.getCode(),
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
