package JUMO.project.Entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Embeddable
class pricePK implements Serializable{
    @Column(name="name")
    private String name;

    @Column(name="time")
    private LocalDateTime time;

    public pricePK(String name, LocalDateTime time){
        this.name=name;
        this.time=time;
    }

    public boolean equals(pricePK pricePK){
        if(this.name.equals(pricePK.getName()) && this.time.equals(pricePK.getTime())){
            return true;
        }
        return false;
    }
}

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="price")
@Embeddable
public class Price implements Comparable<Price>{
    @EmbeddedId
    private pricePK id;

    @Column(name="code")
    private String code;

    @Column(name="High")
    private long High;

    @Column(name="Low")
    private long Low;

    @Column(name="Open")
    private long open;

    @Column(name="Close")
    private long close;

    @Column(name="Volume")
    private long Volume;

    @Column(name="AdjClose")
    private Long adjclose;


    public String getName(){
        return this.getId().getName();
    }

    public LocalDateTime getTime(){
        return this.getId().getTime();
    }

    @Override
    public int compareTo(Price other) {
        return this.getTime().compareTo(other.getTime());
    }
}
