package seunghyun.personalproject.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import seunghyun.personalproject.user.domain.User;
import seunghyun.personalproject.user.dto.AddUserRequestDTO;
import seunghyun.personalproject.user.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user")
    public String signup( AddUserRequestDTO addUserRequestDTO){
        userService.signup(addUserRequestDTO);
        return "redirect:/login";
    }
    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
