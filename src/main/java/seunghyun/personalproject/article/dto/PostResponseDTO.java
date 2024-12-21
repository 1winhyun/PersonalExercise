package seunghyun.personalproject.article.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import seunghyun.personalproject.article.domain.Article;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDTO {
    private String title;
    private String content;

    public static PostResponseDTO from(Article article){
        return PostResponseDTO.builder()
                .title(article.getTitle())
                .content(article.getContent())
                .build();
    }
}
