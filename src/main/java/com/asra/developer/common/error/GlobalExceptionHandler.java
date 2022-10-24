package com.asra.developer.common.error;

import com.asra.developer.models.payload.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorResponse message = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ErrorResponse>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<ErrorResponse> unAuthorizedException(UnAuthorizedException ex, WebRequest request) {
        ErrorResponse message = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), new Date(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ErrorResponse>(message, HttpStatus.UNAUTHORIZED);

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex,
                                                                    WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErrorResponse message = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), new Date(), ex.getMessage(),
                request.getDescription(false), errors);
        return new ResponseEntity<ErrorResponse>(message, HttpStatus.BAD_REQUEST);
    }
}
