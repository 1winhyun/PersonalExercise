package seunghyun.personalproject.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import seunghyun.personalproject.user.domain.User;
import seunghyun.personalproject.user.dto.AddUserRequestDTO;
import seunghyun.personalproject.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Long signup(AddUserRequestDTO requestDTO){
        /*User user=requestDTO.make();
        user.setPassword(bCryptPasswordEncoder.encode(requestDTO.getPassword()));
        return userRepository.save(user);*/

        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        return userRepository.save(User.builder()
                .email(requestDTO.getEmail())
                .password(encoder.encode(requestDTO.getPassword()))
                .build()).getId();
    }

    public User findById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException("unexpected user"));
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(()->new IllegalArgumentException("unexpected user"));
    }
}
