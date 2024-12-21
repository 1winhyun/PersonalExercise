package seunghyun.personalproject.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import seunghyun.personalproject.user.domain.User;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddUserRequestDTO {
    private String email;
    private String password;

    public User make(){
        return User.builder()
                .email(email)
                .password(password)
                .build();
    }
}
