package seunghyun.personalproject.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import seunghyun.personalproject.user.dto.CreateAccessTokenRequestDTO;
import seunghyun.personalproject.user.dto.CreateAccessTokenResponseDTO;
import seunghyun.personalproject.user.service.TokenService;

@RestController
@RequiredArgsConstructor
public class TokenApiController {
    private final TokenService tokenService;

    @PostMapping("/api/token")
    public ResponseEntity<CreateAccessTokenResponseDTO>createNewAccessToken(@RequestBody CreateAccessTokenRequestDTO requestDTO){
        String newAccessToken=tokenService.createNewAccessToken(requestDTO.getRefreshToken());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateAccessTokenResponseDTO(newAccessToken));
    }
}
