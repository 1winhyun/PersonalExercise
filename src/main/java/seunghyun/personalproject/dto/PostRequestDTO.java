package seunghyun.personalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import seunghyun.personalproject.domain.Article;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDTO {
    private String title;
    private String content;

    public Article from(){
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
