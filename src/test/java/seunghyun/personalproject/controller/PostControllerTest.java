package seunghyun.personalproject.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import seunghyun.personalproject.article.domain.Article;
import seunghyun.personalproject.article.dto.PostRequestDTO;
import seunghyun.personalproject.article.repository.PostRepository;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    PostRepository postRepository;

    @BeforeEach
    public void mockMvcSetup(){
        this.mockMvc= MockMvcBuilders.webAppContextSetup(context)
                .build();
        postRepository.deleteAll();
    }

    @DisplayName("게시물 생성 테스트")
    @Test
    public void createTest() throws Exception{
        String url="/api/articles";
        String title="title";
        String content="content";
        PostRequestDTO requestDTO=new PostRequestDTO(title,content);

        String requestBody=objectMapper.writeValueAsString(requestDTO);

        ResultActions result=mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        result.andExpect(status().isCreated());

        List<Article> posts=postRepository.findAll();
        assertThat(posts.size()).isEqualTo(1);
    }
}