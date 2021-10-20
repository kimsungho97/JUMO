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
public class UserService implements UserDetailsService {

    private final UserRepository user_repository;

    @Autowired
    public UserService(UserRepository user_repository){
        this.user_repository=user_repository;
    }

    //회원가입
    public User Signup(User user){
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        //Id 중복 확인
        if(user_repository.findById(user.getId()).isEmpty()){
            user_repository.save(user);
            return user;
        }
        return null;
    }

    //로그인
    public boolean login(User user){
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        if(!user_repository.findById(user.getId()).isEmpty()){
            User result=user_repository.findById(user.getId()).get();
            if(result.getPassword().equals(user.getPassword())){
                return true;
            }
        }
        return false;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return user_repository.findById(username)
                .orElseThrow(()->new UsernameNotFoundException(username));
    }
}
