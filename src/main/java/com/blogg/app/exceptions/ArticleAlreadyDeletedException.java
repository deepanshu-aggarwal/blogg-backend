package com.blogg.app.exceptions;

public class ArticleAlreadyDeletedException extends Exception {
    public ArticleAlreadyDeletedException(String msg) {
        super(msg);
    }
}
