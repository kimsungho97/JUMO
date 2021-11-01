package JUMO.project.Controller.usercontroller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class TotalAssetDTO {
    private String userId;
    // 평가손익
    private Long valuationLoss;
    // 수익률
    private Double valuationLossRate;
    // 평가금액
    private Long valuationAmount;

}
