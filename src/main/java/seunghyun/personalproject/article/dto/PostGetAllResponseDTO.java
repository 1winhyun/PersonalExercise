package seunghyun.personalproject.article.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import seunghyun.personalproject.article.domain.Article;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostGetAllResponseDTO {
    private Long id;
    private String title;
    private String content;

    public static PostGetAllResponseDTO from(Article article){
        return PostGetAllResponseDTO.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .build();
    }
}
