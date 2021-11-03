import React, { useEffect, useState } from "react";
import { useRecoilState } from "recoil";
import { fetchChartData, fetchChartList } from "../../hooks/useChart";
import { fetchUserInfo } from "../../hooks/useMyInfo";
import { fetchCurPrice, fetchTrade } from "../../hooks/useTrade";
import { Loading } from "../../pages";
import { userAtom } from "../../store/user";
import HighChart from "../Chart";
import { config } from "../Chart/chartUtil";

import { AmountInput, Chart, ChartInfo, ChartList, ChartListCode, ChartListData, ChartListGroup, ChartListHead, ChartListInput, ChartListName, ChartTitle, Inner, OrderBtn, OrderRow, ToggleBtn, Trade, TradeToggle } from "./style";

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

    const [userInfo, setUserInfo] = useRecoilState(userAtom);
    

    useEffect(() => {
        async function setStocks() {
            const stocks = await fetchChartList();
            const chartData = await fetchChartData(curStockName);
            setCurrentPrice(await fetchCurPrice(curStockCode));
            setStockList(stocks);
            setChartConfig(config(chartData));
            
            setLoading(false);
        }

        setStocks();
    }, [stockName])

    if (loading)
        return <Loading />
    else
        return (
            <Inner>
                <Chart>
                    <ChartInfo>
                        <ChartTitle
                            fontSize={40}
                            fontWeight={700}
                        >
                            {curStockName}
                        </ChartTitle>
                        <ChartTitle
                            fontSize={20}
                            fontWeight={500}
                        >
                            {currentPrice !== 0 ? currentPrice.toLocaleString("ko-KR") + "원" : ""}
                        </ChartTitle>

                    </ChartInfo>
                    <HighChart configs={chartConfig} />
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
                                        {type==="buy"?(userInfo.balance - totalPrice).toLocaleString("ko-KR"):(userInfo.balance + totalPrice).toLocaleString("ko-KR")}
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
                </Chart>
            
                <ChartList>
                    <ChartListHead>
                        <ChartListInput
                            placeholder={"종목명 입력"}
                            onChange={(e) => setStockName(e.target.value)}
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
                                            setCurrentPrice(await fetchCurPrice(stock.code));
                                            setCurStockName(stock.stockName);
                                            setCurStockCode(stock.code);
                                            setAmount(0);
                                            setTotalPrice(0);
                                            const chartData = await fetchChartData(stock.stockName);
                                            setChartConfig(config(chartData));
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