package JUMO.project.Service;

import JUMO.project.Entity.User;
import JUMO.project.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    //회원가입
    public Optional<User> Signup(String userId, String password){
        // 중복체크
        if (userRepository.findById(userId).isPresent()){
            log.warn("[{}] id has been already used", userId);
//            return new SignupResultDTO(false, "id has been already used");

            return Optional.empty();
        }

        User newUser = User.createUser(userId, bCryptPasswordEncoder.encode(password));
        userRepository.save(newUser);

        return Optional.of(newUser);

//        return new SignupResultDTO(true, "id has been already used");




//        user.setPassword(encoder.encode(user.getPassword()));
//
//        //Id 중복 확인
//        if(userRepository.findById(user.getId()).isEmpty()){
//            userRepository.save(user);
//            return user;
//        }
//        return null;
    }

    //로그인
    public Optional<User> login(String userId, String password){

        Optional<User> findUser = userRepository.findById(userId);
        if (findUser.isEmpty()){
            log.warn("user has not found");
            return findUser;
        }
        User member = findUser.get();

        if (!bCryptPasswordEncoder.matches(password, member.getPassword())) {
            log.warn("password is not correct");
            return findUser;
        }

        return findUser;

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findById(username)
                .orElseThrow(()->new UsernameNotFoundException(username + "has not found"));
    }
}
