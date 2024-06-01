package com.blogg.app.exceptions;

public class UsernameAlreadyTakenException extends Exception {
    public UsernameAlreadyTakenException(String message) {
        super(message);
    }
}
