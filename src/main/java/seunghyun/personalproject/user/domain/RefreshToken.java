package seunghyun.personalproject.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "userId",unique=true,nullable = false)
    private Long userId;

    @Column(name="refresh_token",nullable = false)
    private String refreshToken;

    public RefreshToken(Long userId,String refreshToken){
        this.userId=userId;
        this.refreshToken=refreshToken;
    }

    public RefreshToken update(String newRefreshToken){
        this.refreshToken=newRefreshToken;
        return this;
    }
}
