package JUMO.project.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class StockRow {

    private String code;
    private String name;
    private String time;
    private int Close;
    private int year;
    private int month;
    private int day;
    private double macd;
    private double signalLine;
    private String isFullmaesu;
}
