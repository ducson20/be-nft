package com.asra.developer.models.payload.response;

import java.util.Date;
import java.util.Map;

public class ErrorResponse {

    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;
    private Map<String, String> errors;

    public ErrorResponse(int statusCode, Date timestamp, String message, String description) {
        super();
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }

    public ErrorResponse(int statusCode, Date timestamp, String message, String description, Map<String, String> errors) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
        this.errors = errors;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
