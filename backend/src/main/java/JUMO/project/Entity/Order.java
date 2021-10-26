package JUMO.project.Entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid")
    private User user;

    private String stockId;

    private String stockName;

    private Long eachPrice;

    private Integer count;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private TradeType tradeType;

    public static Order createOrder(User user, String stockId, String stockName, Long eachPrice, Integer count){
        Order order = new Order();
        order.user = user;
        order.stockId = stockId;
        order.stockName = stockName;
        order.eachPrice = eachPrice;
        order.count = count;
        order.orderDate = LocalDateTime.now();
        order.tradeType = TradeType.BUY;

        user.getOrders().add(order);
        return order;
    }

    public void changeTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }

}
