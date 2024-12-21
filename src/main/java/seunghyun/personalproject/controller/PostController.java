package seunghyun.personalproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import seunghyun.personalproject.domain.Article;
import seunghyun.personalproject.dto.PostRequestDTO;
import seunghyun.personalproject.service.PostService;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody PostRequestDTO requestDTO){
        Article post=postService.save(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(post);
    }
}
