package com.rrdlabs.dukaanmanager.exceptions;

import java.util.Arrays;
import java.util.List;

public class ErrorResponse {
    private String message;
    private List<String> errors;

    ErrorResponse(String message, List<String> errors) {
        super();
        this.message = message;
        this.errors = errors;
    }

    ErrorResponse(String message, String error) {
        super();
        this.message = message;
        this.errors = Arrays.asList(error);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
