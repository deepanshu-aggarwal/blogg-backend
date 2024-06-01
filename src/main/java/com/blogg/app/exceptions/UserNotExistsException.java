package com.blogg.app.exceptions;

public class UserNotExistsException extends Exception {
    public UserNotExistsException(String msg) {
        super(msg);
    }
}
