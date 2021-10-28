package JUMO.project.Repository;

import JUMO.project.Entity.Holding;
import JUMO.project.Entity.User;
import JUMO.project.Service.OrderService;
import JUMO.project.Service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Transactional
@SpringBootTest
@Rollback(value = false)
class HoldingRepositoryImplTest {

    @Autowired
    HoldingRepository holdingRepository;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderService orderService;
    @Autowired
    EntityManager em;

    @Rollback(value = false)
    @BeforeEach
    void beforeEach(){
        User newUser = User.createUser("hello", "1234");
        userRepository.save(newUser);
    }

    @Test
    void findHoldingTest(){
        Optional<User> findUser = userRepository.findById("hello");
        User foundUser = findUser.get();
        orderService.buyingOrder(foundUser.getUid(), "035420", "NAVER", 2);

        em.flush();
        em.clear();

        Assertions.assertThat(foundUser.getHoldings().size()).isEqualTo(1);

        List<Holding> holdingList = holdingRepository.findByUserId(foundUser.getUid());
        Assertions.assertThat(holdingList.size()).isEqualTo(1);

    }

}