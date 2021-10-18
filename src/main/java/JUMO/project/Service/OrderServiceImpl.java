package JUMO.project.Service;

import JUMO.project.Entity.Holding;
import JUMO.project.Entity.Order;
import JUMO.project.Entity.TradeType;
import JUMO.project.Entity.User;
import JUMO.project.Repository.HoldingRepository;
import JUMO.project.Repository.OrderRepository;
import JUMO.project.Repository.User_Repository;
import JUMO.project.exception.NoHoldingException;
import JUMO.project.exception.NotEnoughMoneyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final User_Repository userRepository;
    private final HoldingRepository holdingRepository;

    String url = "http://localhost:5000";

    @Override
    public void buyingOrder(Long uid, String stockId, String stockName, Integer count) {

        Long stockPrice = getStockPrice(stockId);

        // 유저 정보 불러오기
        // TODO: 유저 없을 시 예외 발생으로 변경
        Optional<User> optionalUser = userRepository.findByUid(uid);
        if (optionalUser.isEmpty()){
            log.error("there is no user {}",uid);
            return;
        }
        User user = optionalUser.get();

        if(user.getBalance() < stockPrice*count) {
            throw new NotEnoughMoneyException("user have not enough money");
        }

        user.subjectBalance(stockPrice*count);

        // order 객체 생성 및 영속화
        Order order = Order.createOrder(user, stockId, stockName, stockPrice, count);
        orderRepository.save(order);

        // 주문하는 주식 지갑 없으면 새로 생성 및 지갑에 주식 추가
        List<Holding> holdings = holdingRepository.findHoldingByUidStockId(user.getUid(), stockId);
        if (holdings.isEmpty()) {
            Holding holding = Holding.createHolding(user, stockId, stockName, count, stockPrice);
            holdingRepository.save(holding);
            return;
        }
        // 지갑 존재하면 불러오기 및 지갑 갱신
        Holding holding = holdings.get(0);

        holding.addCalHolding(count, stockPrice);

    }


    @Override
    public void sellingOrder(Long uid, String stockId, String stockName, Integer count) {

        Long stockPrice = getStockPrice(stockId);

        // 유저 정보 불러오기
        Optional<User> optionalUser = userRepository.findByUid(uid);
        if (optionalUser.isEmpty()){
            log.error("there is no user {}",uid);
            return;
        }
        User user = optionalUser.get();

        user.addBalance(stockPrice*count);

        // order 객체 생성 및 영속화
        Order order = Order.createOrder(user, stockId, stockName, stockPrice, count);
        order.changeTradeType(TradeType.SELL);
        orderRepository.save(order);

        // TODO: 주문하는 주식 지갑 없으면 에러 발생
        List<Holding> holdings = holdingRepository.findHoldingByUidStockId(user.getUid(), stockId);
        if (holdings.isEmpty()) {
            throw new NoHoldingException("have no holding but sell");
        }
        // 지갑 존재하면 불러오기 및 지갑 갱신
        Holding holding = holdings.get(0);

        holding.subjectCalHolding(count, stockPrice);
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
