import React, {useEffect} from "react";
import HighChart from "../Chart";
import { ChartView, PredictionDiv, PredictionInfo, Predictionlbl, PredictionResult, PredictionSpan, PredictionTitle } from "./style";

export default function ChartContainer() {
    const stockName = new URLSearchParams(window.location.search).get("stockName");
    const stockCode = new URLSearchParams(window.location.search).get("stockCode");

    const shortTermResult = "매수";
    const longTermResult = "매수";
    
    useEffect(() => {
        document.addEventListener("click", candleColorChange);
        candleColorChange();
        return () => {
            document.removeEventListener("click", candleColorChange);
        }
    }, []);

   
    return (
        <>
            <PredictionInfo>
                <PredictionTitle>
                    <Predictionlbl
                        fontSize={50}
                        fontWeight={1200}
                    >
                        {stockName}
                    </Predictionlbl>
                    <Predictionlbl
                        fontSize={32}
                        fontWeight={400}
                    >
                        {stockCode}
                    </Predictionlbl>

                    <PredictionDiv>
                        <PredictionSpan>Short Term Prediction</PredictionSpan>
                        <PredictionResult>{shortTermResult}</PredictionResult>
                    </PredictionDiv>

                    <PredictionDiv>
                        <PredictionSpan>Long Term Prediction</PredictionSpan>
                        <PredictionResult>{longTermResult}</PredictionResult>
                    </PredictionDiv>
                </PredictionTitle>
            </PredictionInfo>
            <ChartView>
                <HighChart
                    stockName={stockName} />
            </ChartView>
        </>
    )
}

//차크 캔들 색깔 변경
function candleColorChange() {
    const candleDown = document.querySelectorAll(".highcharts-point-up");
    const candleUp = document.querySelectorAll(".highcharts-point-down");

    candleDown.forEach((value) => {
        value.style.fill = "#ff3333";
    })

    candleUp.forEach((value) => {
        value.style.fill = "#0000ff";
    })
}