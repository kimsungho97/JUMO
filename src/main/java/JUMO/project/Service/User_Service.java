package JUMO.project.Service;

import JUMO.project.Entity.Order;
import JUMO.project.Entity.User;
import JUMO.project.Repository.User_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class User_Service implements UserDetailsService {

    private final User_Repository user_repository;

    @Autowired
    public User_Service(User_Repository user_repository){
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

//    public List<Order> findOrderList(Long uid) {
//        user_repository
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return user_repository.findById(username)
                .orElseThrow(()->new UsernameNotFoundException(username));
    }
}
