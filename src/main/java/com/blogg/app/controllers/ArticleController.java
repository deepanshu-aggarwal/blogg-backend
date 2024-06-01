package com.blogg.app.controllers;

import com.blogg.app.dto.article.request.NewArticleRequest;
import com.blogg.app.dto.article.response.ArticleResponse;
import com.blogg.app.exceptions.ArticleAlreadyDeletedException;
import com.blogg.app.exceptions.ArticleNotFoundException;
import com.blogg.app.exceptions.PermissionDeniedException;
import com.blogg.app.exceptions.UserNotExistsException;
import com.blogg.app.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> getArticleById(@PathVariable int id) throws ArticleNotFoundException {
        return ResponseEntity.ok(articleService.getArticleById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllArticleForUser(@PathVariable int userId) throws UserNotExistsException {
        return ResponseEntity.ok(articleService.getAllArticlesForUser(userId));
    }

    @PostMapping("/new")
    public ResponseEntity<?> createNewArticle(@RequestBody NewArticleRequest request, @RequestParam int userId) throws UserNotExistsException {
        return ResponseEntity.ok(articleService.createNewArticle(request, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArticleOfUser(@PathVariable int id, @RequestParam int userId) throws ArticleAlreadyDeletedException, ArticleNotFoundException, PermissionDeniedException {
        return ResponseEntity.ok(articleService.deleteArticleOfUser(id, userId));
    }
}
