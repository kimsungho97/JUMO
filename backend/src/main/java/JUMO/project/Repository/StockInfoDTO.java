package JUMO.project.Repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class StockInfoDTO {
    private String code;
    private String stockName;
}
