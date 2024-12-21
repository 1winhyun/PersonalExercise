package seunghyun.personalproject.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import seunghyun.personalproject.user.domain.User;
import seunghyun.personalproject.user.dto.AddUserRequestDTO;
import seunghyun.personalproject.user.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody AddUserRequestDTO addUserRequestDTO){
        User user =userService.addUser(addUserRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(user);
    }
}
