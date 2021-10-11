package JUMO.project.Service;

import JUMO.project.Entity.Order;
import JUMO.project.Entity.User;
import JUMO.project.Repository.HoldingRepository;
import JUMO.project.Repository.OrderRepository;
import JUMO.project.Repository.User_Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final User_Repository userRepository;
    private final HoldingRepository holdingRepository;

    @Override
    public void buyingOrder(Long uid, String stockId, Integer count, Long price) {
        Optional<User> optionalUser = userRepository.findByUid(uid);
        if (optionalUser.isEmpty()){
            log.error("there is no user {}",uid);
            return;
        }
        User user = optionalUser.get();

        Order order = Order.createOrder(user, stockId, price, count);


    }

}
