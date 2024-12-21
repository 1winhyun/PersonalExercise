package seunghyun.personalproject.article.dto;

import java.time.LocalDateTime;
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
public class PostGetOneResponseDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public static PostGetOneResponseDTO from(Article article){
        return PostGetOneResponseDTO.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .createdAt(article.getCreatedAt())
                .build();
    }
}
