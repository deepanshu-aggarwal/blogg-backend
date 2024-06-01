package com.blogg.app.exceptions;

public class ArticleNotFoundException extends Exception {
    public ArticleNotFoundException(String msg) {
        super(msg);
    }
}
