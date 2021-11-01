import React, {useEffect, useState} from "react";
import { fetchPrediction } from "../../hooks/useChart";
import HighChart from "../Chart";
import { ChartView, PredictionDiv, PredictionInfo, Predictionlbl, PredictionResult, PredictionSpan, PredictionTitle } from "./style";

export default function ChartContainer() {
    const stockName = new URLSearchParams(window.location.search).get("stockName");
    const stockCode = new URLSearchParams(window.location.search).get("stockCode");

    const [longResult,setLongReseult] = useState("");
    const [shortResult,setShortReseult] = useState("");


    
    useEffect(() => {
        async function getPredictions(stockName) {
            const result = await fetchPrediction(stockName);
            setLongReseult(result[0]);
            setShortReseult(result[1]);
        }
        getPredictions(stockName);

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
                        <PredictionResult>{shortResult}</PredictionResult>
                    </PredictionDiv>

                    <PredictionDiv>
                        <PredictionSpan>Long Term Prediction</PredictionSpan>
                        <PredictionResult>{longResult}</PredictionResult>
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