package JUMO.project.Entity;

import JUMO.project.exception.NotEnoughHoldingException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table (
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"uid", "stockId"}
                )
        }
)
@Getter
@Setter
public class Holding {
    @Id
    @GeneratedValue
    @Column(name="holding_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid", nullable = false)
    private User user;

    @Column(nullable = false)
    private String stockId;

    @Column(nullable = false)
    private String stockName;

    private Integer count;

    @Column(nullable = false)
    private Long averagePrice;

    @Column(nullable = false)
    private Long volume;

    public static Holding createHolding(User user, String stockId, String stockName, Integer count, Long stockPrice){
        Holding holding = new Holding();
        holding.user = user;
        holding.stockId = stockId;
        holding.stockName = stockName;
        holding.count = count;
        holding.averagePrice = stockPrice;
        holding.volume = stockPrice * count;

        user.getHoldings().add(holding);
        return holding;
    }

    public void addCalHolding(Integer count, Long stockPrice) {
        this.count += count;
        this.volume += stockPrice*count;
        this.averagePrice = this.volume / count;
    }

    public void subjectCalHolding(Integer count, Long stockPrice) throws NotEnoughHoldingException{
        if (this.count - count < 0) {
            throw new NotEnoughHoldingException("sell more than user have");
        }
        this.count -= count;
        this.volume -= stockPrice*count;
        this.averagePrice = this.volume / count;
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
