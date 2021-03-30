package JUMO.project.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="holding")
public class Holding {
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
    public Holding(long uid, long code, long price, long volume){
        this.uid=uid;
        this.code=code;
        this.price=price;
        this.volume=volume;
    }



}
