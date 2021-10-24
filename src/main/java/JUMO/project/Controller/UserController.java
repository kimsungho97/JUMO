package JUMO.project.Controller;

import JUMO.project.Entity.User;
import JUMO.project.Repository.UserRepository;
import JUMO.project.Service.UserServiceImpl;
import JUMO.project.springsecurity.JwtTokenProvider;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@Slf4j
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/api/login")
    public LoginResultDTO login(@RequestBody Map<String, String> user) {
        User member = userRepository.findById(user.get("id"))
                .orElseThrow(() -> new IllegalArgumentException("user not found"));

        if (!bCryptPasswordEncoder.matches(user.get("password"), member.getPassword())) {
            log.warn("password is not correct");
            return new LoginResultDTO(
                    false, member.getId(), member.getBalance(),
                    "password is not correct", "");
        }
        return new LoginResultDTO(
                true, member.getId(), member.getBalance(),
                null, jwtTokenProvider.createJwtAuthToken(member.getId(), member).get());
    }
}
