package JUMO.project.Controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class HoldingInvestmentDTO {
    private String name;
    private Double valuationLoss;
    private Double yield;
    private Integer remainingAmount;
    private Long valuationAmount;
    private Long averagePurchasePrice;
    private Long currentPrice;

    @Override
    public String toString() {
        return "HoldingInvestmentDTO{" +
                "name='" + name + '\'' +
                ", valuationLoss=" + valuationLoss +
                ", yield=" + yield +
                ", remainingAmount=" + remainingAmount +
                ", valuationAmount=" + valuationAmount +
                ", averagePurchasePrice=" + averagePurchasePrice +
                ", currentPrice=" + currentPrice +
                '}';
    }
}
