package JUMO.project.Service;

import JUMO.project.Entity.User;
import JUMO.project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //회원가입
    public User Signup(User user){
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        //Id 중복 확인
        if(userRepository.findById(user.getId()).isEmpty()){
            user.setBalance(1000000L);
            userRepository.save(user);
            return user;
        }
        return null;
    }

    //로그인
    public boolean login(User user){
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        if(userRepository.findById(user.getId()).isPresent()){
            User result= userRepository.findById(user.getId()).get();
            if(result.getPassword().equals(user.getPassword())){
                return true;
            }
        }
        return false;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findById(username)
                .orElseThrow(()->new UsernameNotFoundException(username + "has not found"));
    }
}
