package JUMO.project.Controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
public class OrderDTO {
    private LocalDate date;
    private String stockName;
    private String stockCode;
    private Integer amount;
    private String type;
    private Long total;
}
