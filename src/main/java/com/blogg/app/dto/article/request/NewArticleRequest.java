package com.blogg.app.dto.article.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewArticleRequest {
    private String title;
    private String subtitle;
    private String content;
}
