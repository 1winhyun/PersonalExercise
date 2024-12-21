package seunghyun.personalproject.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seunghyun.personalproject.domain.Article;
import seunghyun.personalproject.dto.PostRequestDTO;
import seunghyun.personalproject.dto.PostResponseDTO;
import seunghyun.personalproject.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Article save(PostRequestDTO requestDTO){
        Article post=requestDTO.make();
        return postRepository.save(post);
    }

    public List<Article> findAll(){
        return postRepository.findAll();
    }

    public Article findById(Long id){
        return postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found"+id));
    }

    public void deleteById(Long id){
        postRepository.deleteById(id);
    }

    public Article update(PostRequestDTO requestDTO,Long id){
        Article post=postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found"+id));
        post.setTitle(requestDTO.getTitle());
        post.setContent(requestDTO.getContent());
        return postRepository.save(post);
    }
}
