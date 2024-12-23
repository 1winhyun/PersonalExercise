package seunghyun.personalproject.user.service;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seunghyun.personalproject.user.config.jwt.TokenProvider;
import seunghyun.personalproject.user.domain.User;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken){
        if(!tokenProvider.validToken(refreshToken)){
            throw new IllegalArgumentException("unexpected token");
        }

        Long userId=refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user=userService.findById(userId);

        return tokenProvider.generateToken(user, Duration.ofDays(14));
    }
}
