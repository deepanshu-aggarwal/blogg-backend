package com.blogg.app.dto.article.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
public class ArticleResponse {
    int id;
    String title;
    String subtitle;
    String content;
    int authorId;
    @JsonProperty("isDeleted")
    boolean isDeleted;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
