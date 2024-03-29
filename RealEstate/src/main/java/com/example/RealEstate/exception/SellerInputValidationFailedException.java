package com.example.RealEstate.exception;

import java.util.List;

public class SellerInputValidationFailedException extends RuntimeException {
    private List<String> errors;

    public SellerInputValidationFailedException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
