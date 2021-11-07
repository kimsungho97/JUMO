import React, {useEffect, useState} from "react";
import { fetchChartData, fetchPrediction } from "../../hooks/useChart";
import { Loading } from "../../pages";
import HighChart from "../Chart";
import { config } from "../Chart/chartUtil";
import { ChartView, Inner, PredictionDiv, PredictionInfo, Predictionlbl, PredictionResult, PredictionSpan, PredictionTitle } from "./style";

export default function ChartContainer() {
    const searchParmas = new URLSearchParams(window.location.search);
    const stockName = searchParmas.get("stockName");
    const stockCode = searchParmas.get("stockCode");

    const [chartConfig, setChartConfig] = useState({});
    const [longResult, setLongResult] = useState("");
    const [shortResult, setShortResult] = useState("");
    const [loading, setLoading] = useState(true);

    
    useEffect(() => {
        async function getPredictions(stockName) {
            
            const result = await fetchPrediction(stockName);
            const chartData = await fetchChartData(stockName);
            
            setLongResult(result[0]);
            setShortResult(result[1]);
            setChartConfig(config(chartData));
            setLoading(false);
        }

        getPredictions(stockName);
    }, []);

    if (loading)
        return <Loading />
    else
        return (
            <Inner>
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
                            <PredictionResult
                                color={shortResult === "SELL" ? "#d80e35" : "#115dcb"}
                            >
                                {shortResult}
                            </PredictionResult>
                        </PredictionDiv>

                        <PredictionDiv>
                            <PredictionSpan>Long Term Prediction</PredictionSpan>
                            <PredictionResult
                                color={longResult === "SELL" ? "#d80e35" : "#115dcb"}
                            >
                                {longResult}
                            </PredictionResult>
                        </PredictionDiv>
                    </PredictionTitle>
                </PredictionInfo>
                <ChartView>
                    <HighChart
                        configs={chartConfig} />
                </ChartView>
            </Inner>
        )
}
