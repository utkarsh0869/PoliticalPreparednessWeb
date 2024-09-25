package com.example.PoliticalPreparedness.exceptions;

public class DuplicateUserException extends RuntimeException {

    public DuplicateUserException(String errorMessage) {
        super(errorMessage);
    }
}
