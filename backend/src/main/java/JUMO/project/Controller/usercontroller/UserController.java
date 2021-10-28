package JUMO.project.Controller.usercontroller;

import JUMO.project.Controller.usercontroller.LoginResultDTO;
import JUMO.project.Controller.usercontroller.SignupResultDTO;
import JUMO.project.Entity.User;
import JUMO.project.Repository.UserRepository;
import JUMO.project.Service.UserServiceImpl;
import JUMO.project.springsecurity.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

    private final UserRepository userRepository;
    private final UserServiceImpl userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/login")
    public LoginResultDTO login(@RequestBody Map<String, String> user) {

        Optional<User> findUser = userService.login(user.get("id"), user.get("password"));
        if (findUser.isPresent()){
            User member = findUser.get();
            return new LoginResultDTO(
                true, member.getId(), member.getBalance(),
                null, jwtTokenProvider.createJwtAuthToken(member.getId(), member).get());
        }
        return new LoginResultDTO(false, null, null, "failed login", null);
    }
    // 회원가입
    @PostMapping("/signup")
    public SignupResultDTO join(@RequestBody Map<String, String> user) {

        Optional<User> newUser = userService.Signup(user.get("id"), user.get("password"));
        if (newUser.isEmpty()){
            return new SignupResultDTO(false, "id has been already used");
        }

        return new SignupResultDTO(true, null);
    }

    @GetMapping("/userinfo")
    public UserInfoDTO userInfo(HttpServletRequest request, HttpServletResponse response){
        String token = jwtTokenProvider.resolveToken(request);
        System.out.println(jwtTokenProvider.getUserUid(token));
        String userId = jwtTokenProvider.getUserId(token);

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()){
            User foundUser = userOptional.get();
            return new UserInfoDTO(true, foundUser.getId(), foundUser.getBalance(), null);
        }
        else{
            log.error("can't find userInfo");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new UserInfoDTO(false, null, null, "can't find userInfo");
        }
    }
}
