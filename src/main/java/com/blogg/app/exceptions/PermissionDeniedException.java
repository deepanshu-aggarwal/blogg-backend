package com.blogg.app.exceptions;

public class PermissionDeniedException extends Exception{
    public PermissionDeniedException(String msg){
        super(msg);
    }
}
