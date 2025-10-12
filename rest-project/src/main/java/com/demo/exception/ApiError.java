package com.demo.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {
    private OffsetDateTime timestamp = OffsetDateTime.now();
    private int status;
    private String error;
    private String message;
    private String path;
    private List<FieldError> errors = new ArrayList<>();

    public static class FieldError {
        private String field;
        private String message;
        private Object rejectedValue;
        public FieldError() {}
        public FieldError(String field, String message, Object rejectedValue) {
            this.field = field; this.message = message; this.rejectedValue = rejectedValue;
        }
        public String getField() { return field; }
        public void setField(String field) { this.field = field; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public Object getRejectedValue() { return rejectedValue; }
        public void setRejectedValue(Object rejectedValue) { this.rejectedValue = rejectedValue; }
    }

    public ApiError() { }
    public ApiError(int status, String error, String message, String path) {
        this.status = status; this.error = error; this.message = message; this.path = path;
    }

    public void addFieldError(String field, String message, Object rejectedValue) {
        this.errors.add(new FieldError(field, message, rejectedValue));
    }

    public OffsetDateTime getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
    public List<FieldError> getErrors() { return errors; }
    public void setErrors(List<FieldError> errors) { this.errors = errors; }
}
