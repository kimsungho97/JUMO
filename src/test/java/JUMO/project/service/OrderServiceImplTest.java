package JUMO.project.service;

import JUMO.project.Entity.User;
import JUMO.project.Repository.OrderRepository;
import JUMO.project.Repository.User_Repository;
import JUMO.project.Service.OrderService;
import JUMO.project.exception.NoHoldingException;
import JUMO.project.exception.NotEnoughHoldingException;
import JUMO.project.exception.NotEnoughMoneyException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
//@Rollback(false)
class OrderServiceImplTest {

    @Autowired
    OrderService orderservice;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    User_Repository userRepository;

//    @BeforeEach
//    public void beforeEach(){
//        User user = User.createUser("kjpark", "1234");
//        userRepository.save(user);
//    }

    @Test
    public void getPriceTest(){
        Long price = orderservice.getStockPrice("005930");
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("price = " + price);
        System.out.println("dateTime = " + dateTime);
        System.out.println("price = " + price);
    }

    @Rollback(value = false)
    @Test
    void buyingAndSellingOrderTest() {
        User user = User.createUser("kjpark", "1234");
        User user1 = User.createUser("kimsh", "q1w2e3");
        userRepository.save(user1);
        userRepository.save(user);
        user.addBalance(10000000L);
        user1.addBalance(100000000L);


        orderservice.buyingOrder(user.getUid(),"035420", "네이버", 4);
        orderservice.buyingOrder(user.getUid(),"035420", "네이버", 4);
        orderservice.sellingOrder(user.getUid(), "035420", "네이버", 3);

        orderservice.buyingOrder(user1.getUid(), "035720", "카카오", 5);


    }

    @Test()
    void notEnoughMoneyTest() {
        User user = User.createUser("abc", "12345");
        userRepository.save(user);
        user.addBalance(10L);
        org.junit.jupiter.api.Assertions.assertThrows(
                NotEnoughMoneyException.class,
                () ->{
                    orderservice.buyingOrder(user.getUid(),"035420", "네이버", 4);
                }
        );

    }

    @Test()
    void noHoldingExceptionTest() {
        User user = User.createUser("abc", "12345");
        userRepository.save(user);
        user.addBalance(10L);
        org.junit.jupiter.api.Assertions.assertThrows(
                NoHoldingException.class,
                () ->{
                    orderservice.sellingOrder(user.getUid(),"035420", "네이버", 4);
                }
        );

    }

    @Test
    void notEnoughHoldingTest(){
        User user = User.createUser("abc", "12345");
        userRepository.save(user);
        user.addBalance(10000000L);
        orderservice.buyingOrder(user.getUid(), "035420", "네이버", 1);
        org.junit.jupiter.api.Assertions.assertThrows(
                NotEnoughHoldingException.class,
                () ->{
                    orderservice.sellingOrder(user.getUid(),"035420", "네이버", 3);
                }
        );
    }


}