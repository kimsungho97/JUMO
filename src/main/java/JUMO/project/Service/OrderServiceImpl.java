package JUMO.project.Service;

import JUMO.project.Entity.Order;
import JUMO.project.Entity.StockRow;
import JUMO.project.Entity.User;
import JUMO.project.Repository.HoldingRepository;
import JUMO.project.Repository.OrderRepository;
import JUMO.project.Repository.User_Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final User_Repository userRepository;
    private final HoldingRepository holdingRepository;

    String url = "http://localhost:5000";

    @Override
    public void buyingOrder(Long uid, String stockId, Integer count) {

        Long stockPrice = getStockPrice(stockId);

        Optional<User> optionalUser = userRepository.findByUid(uid);
        if (optionalUser.isEmpty()){
            log.error("there is no user {}",uid);
            return;
        }
        User user = optionalUser.get();

        Order order = Order.createOrder(user, stockId, stockPrice, count);


    }

    @Override
    public void sellOrder(Long uid, String stockId, Integer count) {

    }

    public Long getStockPrice(String stockId){
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url + "/curprice")
                .queryParam("code", stockId)
                .build(false);  // 인코딩 하지않음
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(5000); // api 호출 타임아웃
        factory.setReadTimeout(5000);   // api 읽기 타임아웃

        RestTemplate template = new RestTemplate(factory);
        ResponseEntity<StockPriceDTO> response = template.exchange(
                uriComponents.toUriString(), HttpMethod.GET, new HttpEntity<String>(headers), StockPriceDTO.class);
        Long stockPrice = response.getBody().getPrice();
        return stockPrice;
    }

}
