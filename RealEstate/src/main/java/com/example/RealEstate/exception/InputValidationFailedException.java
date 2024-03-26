package com.example.RealEstate.exception;

import java.util.List;

public class InputValidationFailedException extends RuntimeException {
    private List<String> errors;

    public InputValidationFailedException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
