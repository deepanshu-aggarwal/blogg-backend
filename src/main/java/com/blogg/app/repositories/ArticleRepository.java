package com.blogg.app.repositories;

import com.blogg.app.dao.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    @Query("select a from Article a where a.author.id = :userId and a.isDeleted = false")
    public List<Article> findAllByAuthorId(int userId);
}
