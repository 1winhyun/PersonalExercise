package seunghyun.personalproject.user.config.oauth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import seunghyun.personalproject.user.config.jwt.TokenProvider;
import seunghyun.personalproject.user.domain.RefreshToken;
import seunghyun.personalproject.user.domain.User;
import seunghyun.personalproject.user.repository.RefreshTokenRepository;
import seunghyun.personalproject.user.service.UserService;
import seunghyun.personalproject.user.util.CookieUtil;

@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    public static final String REFRESH_TOKEN_COOKIE_NAME="refresh_token";
    public static final Duration REFRESH_TOKEN_DURATION=Duration.ofDays(14);
    public static final Duration ACCESS_TOKEN_DURATION=Duration.ofDays(1);
    public static final String REDIRECT_PATH="/articles";

    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final OAuth2AuthorizationRequestBasedOnCookieRepository authorizationRequestRepository;
    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException{
        OAuth2User oAuth2User=(OAuth2User) authentication.getPrincipal();
        User user=userService.findByEmail((String) oAuth2User.getAttributes().get("email"));

        String refreshToken=tokenProvider.generateToken(user,REFRESH_TOKEN_DURATION);
        saveRefreshToken(user.getId(),refreshToken);
        addRefreshTokenToCookie(request,response,refreshToken);

        String accessToken=tokenProvider.generateToken(user,ACCESS_TOKEN_DURATION);
        String targetUrl=getTargetUrl(accessToken);
        clearAuthenticationAttributes(request,response);
        getRedirectStrategy().sendRedirect(request,response,targetUrl);
    }

    private void saveRefreshToken(Long userId,String token){
        RefreshToken refreshToken=refreshTokenRepository.findByUserId(userId)
                .map(entity->entity.update(token))
                .orElse(new RefreshToken(userId,token));
    }

    private void addRefreshTokenToCookie(HttpServletRequest request,HttpServletResponse response,String refreshToken){
        int maxAge=(int)REFRESH_TOKEN_DURATION.toSeconds();
        CookieUtil.deleteCookie(request,response,REFRESH_TOKEN_COOKIE_NAME);
        CookieUtil.addCookie(response,REFRESH_TOKEN_COOKIE_NAME,refreshToken,maxAge);
    }

    private void clearAuthenticationAttributes(HttpServletRequest request,HttpServletResponse response){
        super.clearAuthenticationAttributes(request);
        authorizationRequestRepository.removeAuthorizationRequest(request,response);
    }

    private String getTargetUrl(String token){
        return UriComponentsBuilder.fromUriString(REDIRECT_PATH)
                .queryParam("token",token)
                .build()
                .toUriString();
    }
}
