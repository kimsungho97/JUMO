package JUMO.project.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Holding {
    @Id
    @Column(name="holding_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "uid", nullable = false)
    private User user;

    @Column(nullable = false)
    private Long stockId;

    @Column(nullable = false)
    private String stockName;

    private Integer count;

    @Column(nullable = false)
    private Long averagePrice;

    @Column(nullable = false)
    private Long volume;

    public static Holding createHolding(User user, Long stockId, String stockName, Integer count){
        Holding holding = new Holding();
        holding.user = user;
        holding.stockId = stockId;
        holding.stockName = stockName;
        holding.count = count;

        user.getHoldings().add(holding);
        return holding;
    }

//    @CreatedDate
//    @Column(name="datetime",nullable = false, updatable = false)
//    private LocalDateTime datetime;

//    @Builder
//    public Holding(long uid, long code, long price, long volume){
//        this.uid=uid;
//        this.code=code;
//        this.price=price;
//        this.volume=volume;
//    }

}
