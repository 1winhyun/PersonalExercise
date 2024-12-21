package seunghyun.personalproject.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import seunghyun.personalproject.domain.Article;
import seunghyun.personalproject.dto.PostRequestDTO;
import seunghyun.personalproject.dto.PostResponseDTO;
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

    @GetMapping("api/articles")
    public ResponseEntity<List<PostResponseDTO>>findAllPosts(){
        List<PostResponseDTO>posts=postService.findAll()
                .stream()
                .map(PostResponseDTO::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok()
                .body(posts);
    }
}
