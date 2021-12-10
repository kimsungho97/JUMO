import React, { useEffect, useState } from "react";
import { useRecoilState } from "recoil";
import { fetchChartData, fetchChartList, fetchPrediction } from "../../hooks/useChart";
import { fetchUserInfo } from "../../hooks/useMyInfo";
import { fetchCurPrice, fetchTrade } from "../../hooks/useTrade";
import { Loading } from "../../pages";
import { userAtom } from "../../store/user";
import {HighChart} from "../Chart";
import { config } from "../Chart/chartUtil";

import { AmountInput, Chart, ChartGroup, ChartHeader, ChartInfo, ChartList, ChartListCode, ChartListData, ChartListGroup, ChartListHead, ChartListInput, ChartListName, ChartTitle, Inner, OrderBtn, OrderRow, Prediction, PredictionDiv, PredictionResult, PredictionSpan, PredictionSpec, ToggleBtn, Trade, TradeToggle } from "./style";

export default function SimulateTrade() {
    const [curStockName, setCurStockName] = useState("삼성전자");
    const [curStockCode, setCurStockCode] = useState("005930.KS");
    const [stockName, setStockName] = useState("");
    const [type, setType] = useState("buy");
    const [amount, setAmount] = useState(0);
    const [currentPrice, setCurrentPrice] = useState(0);
    const [totalPrice, setTotalPrice] = useState(0);
    const [stockList, setStockList] = useState([]);
    const [chartConfig, setChartConfig] = useState({});
    const [loading, setLoading] = useState(true);
    const [longResult, setLongResult] = useState("");
    const [shortResult, setShortResult] = useState("");
    const [userInfo, setUserInfo] = useRecoilState(userAtom);
    

    useEffect(() => {
        async function setStocks() {
            const stocks = await fetchChartList();
            const chartData = await fetchChartData(curStockName);
            setCurrentPrice(await fetchCurPrice(curStockCode));
            setStockList(stocks);
            setChartConfig(config(chartData));

            const result = await fetchPrediction(curStockName);
            setLongResult(result[0]);
            setShortResult(result[1]);

            setLoading(false);
        }

        setStocks();
    }, [])

    if (loading)
        return <Loading />
    else
        return (
            <Inner>
                <Chart>
                    <ChartHeader>
                        <ChartGroup>
                            <ChartInfo>
                                <ChartTitle
                                    fontSize={50}
                                    fontWeight={700}
                                    width={curStockName.length*50+10}
                                >
                                    {curStockName}
                                </ChartTitle>
                                <ChartTitle
                                    fontSize={30}
                                    fontWeight={500}
                                    width={curStockName.length*50+10}
                                >
                                    {curStockCode}
                                </ChartTitle>
                                <ChartTitle
                                    fontSize={40}
                                    fontWeight={500}
                                >
                                    {currentPrice !== 0 ? currentPrice.toLocaleString("ko-KR") + "원" : ""}
                                </ChartTitle>
                            </ChartInfo>
                            <Prediction>
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
                                <PredictionDiv>
                                    <PredictionSpec>
                                        26일치 EMA와 12일치 EMA로 MACD지표를 만들었으며 <br/>9일치 EMA로 Signal 지표를 만들어서 비교함
                                    </PredictionSpec>
                                </PredictionDiv>
                            </Prediction>
                        </ChartGroup>
                        {
                            curStockName !== '' ? (
                                <Trade>
                                    <TradeToggle>
                                        <ToggleBtn
                                            color={type === "buy" ? "#d80e35" : "grey"}
                                            value={"buy"}
                                            onClick={(e) => {
                                                e.preventDefault();
                                                setType(e.target.value);
                                            }}
                                        >
                                            매수
                                        </ToggleBtn>
                                        <ToggleBtn
                                            color={type === "sell" ? "#115dcb" : "grey"}
                                            value={"sell"}
                                            onClick={(e) => {
                                                e.preventDefault();
                                                setType(e.target.value);
                                            }}
                                        >
                                            매도
                                        </ToggleBtn>
                                    </TradeToggle>
                                    <OrderRow>
                                        <label>수량</label>
                                        <AmountInput
                                            type={"number"}
                                            value={amount}
                                            onChange={(e) => {
                                                if (e.target.value.length === 0) {
                                                    setAmount("");
                                                    setTotalPrice(0);
                                                }
                                                else {
                                                    setAmount(parseInt(e.target.value));
                                                    setTotalPrice(parseInt(e.target.value) * currentPrice)
                                                }
                                            }}
                                        />
                                    </OrderRow>
                                    <OrderRow>
                                        <label>총액</label>
                                        <span>{totalPrice.toLocaleString("ko-KR")}</span>
                                    </OrderRow>
                                    <OrderRow>
                                        <label>현재 가능 금액</label>
                                        <span>{userInfo.balance.toLocaleString("ko-KR")}</span>
                                    </OrderRow>
                                    <OrderRow
                                        style={{ borderTop: '1px #DDDDDD solid' }}
                                    >
                                        <label>거래 후 잔액</label>
                                        <span>
                                            {type === "buy" ? (userInfo.balance - totalPrice).toLocaleString("ko-KR") : (userInfo.balance + totalPrice).toLocaleString("ko-KR")}
                                        </span>
                                    </OrderRow>
                                    <OrderRow>
                                        <OrderBtn
                                            onClick={async (e) => {
                                                e.preventDefault();
                                                const result = await fetchTrade(type, curStockName, curStockCode, amount);
                                                console.log(result);
                                                if (result.result === false) {
                                                    alert(result.msg);
                                                }
                                                else {
                                                    alert("거래가 체결되었습니다.");
                                                    const userData = await fetchUserInfo();
                                                    setUserInfo({ userId: userData.id, balance: userData.balance });
                                                    setAmount(0);
                                                }
                                            }}
                                        >
                                            주문
                                        </OrderBtn>
                                    </OrderRow>
                
                                </Trade>
                            ) : (<></>)
                        }
                    </ChartHeader>
                    <div style={{ margin: "10px",marginTop: "20px" }}>
                        <HighChart configs={chartConfig} name={curStockName}/>
                    </div>
                </Chart>
            
                <ChartList>
                    <ChartListHead>
                        <ChartListInput
                            placeholder={"종목명 입력"}
                            onChange={(e) => {
                                e.preventDefault();
                                setStockName(e.target.value);
                            }}
                        />
                    </ChartListHead>
                    <ChartListGroup>
                        {
                            stockList.map((stock, index) => {
                                return (
                                    <ChartListData
                                        key={index}
                                        display={rowFiltered(stock.stockName, stockName)}
                                        value={stock.name}
                                        color={(curStockName === stock.stockName)}
                                        onClick={async (e) => {
                                            setLoading(true);
                                            setCurrentPrice(await fetchCurPrice(stock.code));
                                            setCurStockName(stock.stockName);
                                            setCurStockCode(stock.code);
                                            const predictions = await fetchPrediction(stock.stockName);
                                            setLongResult(predictions[0]);
                                            setShortResult(predictions[1]);
                                            setAmount(0);
                                            setTotalPrice(0);
                                            const chartData = await fetchChartData(stock.stockName);
                                            setChartConfig(config(chartData));
                                            setLoading(false);
                                            }
                                        }
                                    >
                                        <ChartListName>
                                            {stock.stockName}
                                        </ChartListName>
                                        <ChartListCode>
                                            {stock.code}
                                        </ChartListCode>
                                    </ChartListData>
                                )
                            })
                        }
                    </ChartListGroup>
                </ChartList>

            </Inner>
        )
}

const rowFiltered = (stockName, name) => {
    if (stockName.indexOf(name) === -1)
        return "none";
    else
        return "flex";
}