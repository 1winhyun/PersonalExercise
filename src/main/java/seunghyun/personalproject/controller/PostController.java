package seunghyun.personalproject.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seunghyun.personalproject.domain.Article;
import seunghyun.personalproject.dto.PostRequestDTO;
import seunghyun.personalproject.dto.PostResponseDTO;
import seunghyun.personalproject.service.PostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles")
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Article> create(@RequestBody PostRequestDTO requestDTO){
        Article post=postService.save(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(post);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>>findAllPosts(){
        List<PostResponseDTO>posts=postService.findAll()
                .stream()
                .map(PostResponseDTO::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok()
                .body(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO>findPostById(@PathVariable Long id){
        Article post=postService.findById(id);
        return ResponseEntity.ok()
                .body(PostResponseDTO.from(post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deletePostById(@PathVariable Long id){
        postService.deleteById(id);
        return ResponseEntity.ok()
                .build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody PostRequestDTO requestDTO){
        Article post=postService.update(requestDTO,id);
        return ResponseEntity.ok()
                .body(post);
    }
}
