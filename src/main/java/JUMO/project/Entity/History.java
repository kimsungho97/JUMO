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
@Table(name="history")
@Embeddable
public class History {
    @Id
    @Column(name="id")
    private long id;
    @Column(name="uid",nullable = false)
    private long uid;
    @Column(name="code",nullable = false)
    private long code;
    @Column(name="price",nullable = false)
    private long price;
    @Column(name="volume",nullable = false)
    private long volume;
    @CreatedDate
    @Column(name="datetime",nullable = false, updatable = false)
    private LocalDateTime datetime;

    @Builder
    public History(long uid, long code, long price, long volume){
        this.uid=uid;
        this.code=code;
        this.price=price;
        this.volume=volume;
    }

    public History(Holding holding){
        this.uid=holding.getUid();
        this.code=holding.getCode();
        this.price=holding.getPrice();
        this.volume=holding.getVolume();
    }
}
