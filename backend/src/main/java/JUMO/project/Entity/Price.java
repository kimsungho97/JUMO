package JUMO.project.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Embeddable
class pricePK implements Serializable{

    @Column(name="name")
    private String name;

    @Column(name="time")
    private LocalDateTime time;


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
    @EmbeddedId @Id
    private pricePK id;

    @Column(name="code")
    private String code;

    @Column(name="High")
    private Long high;

    @Column(name="Low")
    private Long low;

    @Column(name="Open")
    private Long open;

    @Column(name="Close")
    private Long close;

    @Column(name="Volume")
    private Long volume;

    @Column(name="AdjClose")
    private Long adjClose;


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
