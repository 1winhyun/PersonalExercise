package seunghyun.personalproject.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seunghyun.personalproject.user.domain.RefreshToken;
import seunghyun.personalproject.user.repository.RefreshTokenRepository;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken){
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(()-> new IllegalArgumentException("unexpected token"));
    }
}
