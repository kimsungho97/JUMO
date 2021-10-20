package JUMO.project.Repository;

import JUMO.project.Entity.Price;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.AttributeOverride;
import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PriceRepositoryImplTest {
    @Autowired
    PriceRepository priceRepository;
    @Autowired
    EntityManager em;

    @Test
    public void findByNameTest(){
        List<Price> prices = priceRepository.findByName("카");
        for (Price price : prices) {
            System.out.println("price = " + price);
        }
        System.out.println("prices.size() = " + prices.size());
    }

    @Test
    public void findAllTest(){
        List<Price> prices = priceRepository.findAll();
        System.out.println("prices.size() = " + prices.size());
    }

}