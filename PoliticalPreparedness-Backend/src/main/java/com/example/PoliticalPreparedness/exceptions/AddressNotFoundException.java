package com.example.PoliticalPreparedness.exceptions;

public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
