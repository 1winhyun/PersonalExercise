package seunghyun.personalproject.user.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
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
import seunghyun.personalproject.user.config.jwt.JwtFactory;
import seunghyun.personalproject.user.config.jwt.JwtProperties;
import seunghyun.personalproject.user.domain.RefreshToken;
import seunghyun.personalproject.user.domain.User;
import seunghyun.personalproject.user.dto.CreateAccessTokenRequestDTO;
import seunghyun.personalproject.user.repository.RefreshTokenRepository;
import seunghyun.personalproject.user.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
class TokenApiControllerTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    JwtProperties jwtProperties;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @BeforeEach
    public void mockMvcSetUp(){
        this.mockMvc= MockMvcBuilders.webAppContextSetup(context)
                .build();
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("토큰 발급")
    public void createNewAccessToken() throws Exception{
        final String url="/api/token";

        User testUser=userRepository.save(User.builder()
                .email("seunghyun135@gmail.com")
                .password("seunghyun")
                .build());

        String refreshToken= JwtFactory.builder()
                .claims(Map.of("id",testUser.getId()))
                .build()
                .createToken(jwtProperties);
        refreshTokenRepository.save(new RefreshToken(testUser.getId(),refreshToken));

        CreateAccessTokenRequestDTO requestDTO=new CreateAccessTokenRequestDTO();
        requestDTO.setRefreshToken(refreshToken);
        final String requestBody=objectMapper.writeValueAsString(requestDTO);

        ResultActions resultActions=mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accessToken").isNotEmpty());
    }
}