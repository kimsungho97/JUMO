package JUMO.project.Entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "uid")
    private User user;

    private String stockId;

    private String stockName;

    private Long eachPrice;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private TradeType tradeType;

}
