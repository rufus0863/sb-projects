package com.demo.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(NotFoundException ex, HttpServletRequest req) {
        ApiError body = new ApiError(HttpStatus.NOT_FOUND.value(), "Not Found", ex.getMessage(), req.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiError> handleConflict(ConflictException ex, HttpServletRequest req) {
        ApiError body = new ApiError(HttpStatus.CONFLICT.value(), "Conflict", ex.getMessage(), req.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrity(DataIntegrityViolationException ex, HttpServletRequest req) {
        ApiError body = new ApiError(HttpStatus.CONFLICT.value(), "Conflict", "Data integrity violation", req.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest req) {
        ApiError body = new ApiError(HttpStatus.BAD_REQUEST.value(), "Bad Request", "Validation failed", req.getRequestURI());
        for (ConstraintViolation<?> v : ex.getConstraintViolations()) {
            body.addFieldError(String.valueOf(v.getPropertyPath()), v.getMessage(), v.getInvalidValue());
        }
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleTypeMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest req) {
        ApiError body = new ApiError(HttpStatus.BAD_REQUEST.value(), "Bad Request",
                "Parameter '" + ex.getName() + "' must be of type " + (ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "N/A"),
                req.getRequestURI());
        return ResponseEntity.badRequest().body(body);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                         HttpHeaders headers,
                                                                         HttpStatusCode status,
                                                                         org.springframework.web.context.request.WebRequest request) {
        ApiError body = new ApiError(HttpStatus.BAD_REQUEST.value(), "Bad Request",
                "Missing required parameter: " + ex.getParameterName(),
                request.getDescription(false).replace("uri=", ""));
        return ResponseEntity.badRequest().body(body);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  org.springframework.web.context.request.WebRequest request) {
        ApiError body = new ApiError(HttpStatus.BAD_REQUEST.value(), "Bad Request", "Validation failed",
                request.getDescription(false).replace("uri=", ""));
        ex.getBindingResult().getFieldErrors().forEach(fe -> body.addFieldError(fe.getField(), fe.getDefaultMessage(), fe.getRejectedValue()));
        return ResponseEntity.badRequest().body(body);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatusCode status,
                                                         org.springframework.web.context.request.WebRequest request) {
        ApiError body = new ApiError(HttpStatus.BAD_REQUEST.value(), "Bad Request", "Validation failed",
                request.getDescription(false).replace("uri=", ""));
        ex.getBindingResult().getFieldErrors().forEach(fe -> body.addFieldError(fe.getField(), fe.getDefaultMessage(), fe.getRejectedValue()));
        return ResponseEntity.badRequest().body(body);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  org.springframework.web.context.request.WebRequest request) {
        ApiError body = new ApiError(HttpStatus.BAD_REQUEST.value(), "Bad Request",
                "Malformed JSON request",
                request.getDescription(false).replace("uri=", ""));
        return ResponseEntity.badRequest().body(body);
    }
}
