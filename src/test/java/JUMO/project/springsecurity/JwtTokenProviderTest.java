package JUMO.project.springsecurity;

import JUMO.project.Entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtTokenProviderTest {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Test
    void tokenTest(){
        User user = User.createUser("hello", "1234");

        Optional<String> token = jwtTokenProvider.createJwtAuthToken("hello", user);
        System.out.println("token.get() = " + token.get());

        String userId = jwtTokenProvider.getUserId(token.get());
        System.out.println("userId = " + userId);
    }


}