package seunghyun.personalproject.user.config.jwt;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import io.jsonwebtoken.Jwts;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import seunghyun.personalproject.user.domain.User;
import seunghyun.personalproject.user.repository.UserRepository;

@SpringBootTest
@Slf4j
class TokenProviderTest {
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProperties jwtProperties;

    @DisplayName("generateToken(): 유저 정보와 만료 기간을 전달해 토큰을 만들 수 있다.")
    @Test
    void generateToken(){
        User testUser=userRepository.save(User.builder()
                .email("jimking1@naver.com")
                .password("jimking1")
                .build());

        String token=tokenProvider.generateToken(testUser, Duration.ofDays(14));

        Long userId= Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .get("id", Long.class);

        assertThat(userId).isEqualTo(testUser.getId());
        log.info("userId={}",userId);
        log.info("testId={}",testUser.getId());
    }
}