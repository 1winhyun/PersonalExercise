package seunghyun.personalproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seunghyun.personalproject.domain.Article;
import seunghyun.personalproject.dto.PostRequestDTO;
import seunghyun.personalproject.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Article save(PostRequestDTO requestDTO){
        return postRepository.save(requestDTO.from());
    }
}
