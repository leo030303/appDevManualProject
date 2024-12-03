package com.example.app_labs.services.exceptions;

public class NonexistentRecordException extends Exception{
    public NonexistentRecordException(String message) {
        super(message);
    }
}
