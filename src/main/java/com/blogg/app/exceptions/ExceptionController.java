package com.blogg.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(UserNotCreatedException.class)
    public ResponseEntity<String> _handleUserNotCreatedException(UserNotCreatedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameAlreadyTakenException.class)
    public ResponseEntity<String> _handleUsernameAlreadyTakenException(UsernameAlreadyTakenException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> _handleEmailAlreadyExistsException(EmailAlreadyExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotExistsException.class)
    public ResponseEntity<String> _handleUserNotExistsException(UserNotExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<String> _handleArticleNotFoundException(ArticleNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArticleAlreadyDeletedException.class)
    public ResponseEntity<String> _handleArticleAlreadyDeletedException(ArticleAlreadyDeletedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PermissionDeniedException.class)
    public ResponseEntity<String> _handlePermissionDeniedException(PermissionDeniedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }
}
