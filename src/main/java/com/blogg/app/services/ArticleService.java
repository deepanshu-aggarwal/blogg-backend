package com.blogg.app.services;

import com.blogg.app.dao.Article;
import com.blogg.app.dao.User;
import com.blogg.app.dto.article.request.NewArticleRequest;
import com.blogg.app.dto.article.response.ArticleResponse;
import com.blogg.app.exceptions.ArticleAlreadyDeletedException;
import com.blogg.app.exceptions.ArticleNotFoundException;
import com.blogg.app.exceptions.PermissionDeniedException;
import com.blogg.app.exceptions.UserNotExistsException;
import com.blogg.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blogg.app.repositories.ArticleRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private UserRepository userRepository;

    public ArticleResponse getArticleById(int id) throws ArticleNotFoundException {
        Article article = articleRepository.findById(id).orElse(null);
        if (article == null || article.isDeleted()) {
            throw new ArticleNotFoundException("Article with id " + id + " not found.");
        }

        return ArticleResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .subtitle(article.getSubtitle())
                .content(article.getContent())
                .authorId(article.getAuthor().getId())
                .isDeleted(article.isDeleted())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt()).build();
    }

    public List<Article> getAllArticlesForUser(int userId) throws UserNotExistsException {
        // check if user exists in user table
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new UserNotExistsException("User not found");
        }

        // fetch all articles for that user
        return articleRepository.findAllByAuthorId(userId);
    }

    public String createNewArticle(NewArticleRequest request, int userId) throws UserNotExistsException {
        // check if user exists or not
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new UserNotExistsException("User not found.");
        }

        LocalDateTime currentTime = LocalDateTime.now();

        Article article = new Article();
        article.setAuthor(user);
        article.setTitle(request.getTitle());
        article.setSubtitle(request.getSubtitle());
        article.setContent(request.getContent());
        article.setCreatedAt(currentTime);
        article.setUpdatedAt(currentTime);
        articleRepository.save(article);

        return "New article created.";
    }

    public String deleteArticleOfUser(int id, int userId) throws ArticleNotFoundException, ArticleAlreadyDeletedException, PermissionDeniedException {
        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            throw new ArticleNotFoundException("Article you are trying to delete not found.");
        } else if(article.isDeleted()){
            throw new ArticleAlreadyDeletedException("Article you are trying to delete is already deleted.");
        }

        if(article.getAuthor().getId() != userId){
            throw new PermissionDeniedException("User " + userId + " is not authorized to delete this article.");
        }

        article.setDeleted(true);
        articleRepository.save(article);

        return "Article delete successfully.";
    }
}
