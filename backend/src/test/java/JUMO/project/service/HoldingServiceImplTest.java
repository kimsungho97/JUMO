package JUMO.project.service;

import JUMO.project.Controller.HoldingInvestmentDTO;
import JUMO.project.Entity.Holding;
import JUMO.project.Entity.User;
import JUMO.project.Repository.HoldingRepository;
import JUMO.project.Repository.UserRepository;
import JUMO.project.Service.HoldingService;
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

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class HoldingServiceImplTest {

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
    @Autowired
    HoldingService holdingService;

    @Rollback(value = false)
    @BeforeEach
    void beforeEach(){
        User newUser = User.createUser("hello", "1234");
        userRepository.save(newUser);
    }

    @Test
    void investmentInfoTest(){
        Optional<User> findUser = userRepository.findById("hello");
        User foundUser = findUser.get();
        orderService.buyingOrder(foundUser.getUid(), "035420", "NAVER", 2);
        em.flush();
        em.clear();

        List<HoldingInvestmentDTO> userAllHoldingInfo = holdingService.findUserAllHoldingInfo(1L);
        for(var dto : userAllHoldingInfo){
            System.out.println("dto = " + dto);
        }
    }
}