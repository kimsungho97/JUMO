package JUMO.project.service;

import JUMO.project.Service.OrderService;
import JUMO.project.Service.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    OrderService os;

    @Test
    public void getPriceTest(){
        Long price = os.getStockPrice("005930");
        Assertions.assertThat(price).isEqualTo(71500);
    }
}