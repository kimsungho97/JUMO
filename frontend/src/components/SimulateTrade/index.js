import React, { useEffect, useState } from "react";
import { useSetRecoilState } from "recoil";
import { fetchChartData, fetchChartList } from "../../hooks/useChart";
import { fetchUserInfo } from "../../hooks/useMyInfo";
import { fetchCurPrice, fetchTrade } from "../../hooks/useTrade";
import { userAtom } from "../../store/user";
import HighChart from "../Chart";
import { config } from "../Chart/chartUtil";

import { AmountInput, Chart, ChartInfo, ChartList, ChartListCode, ChartListData, ChartListGroup, ChartListHead, ChartListInput, ChartListName, ChartTitle, Inner, OrderBtn, ToggleBtn, Trade, TradeToggle } from "./style";

export default function SimulateTrade() {
    const setUserInfo = useSetRecoilState(userAtom);
    const [curStockName, setCurStockName] = useState("");
    const [curStockCode, setCurStockCode] = useState("");
    const [stockName, setStockName] = useState("");
    const [type, setType] = useState("buy");
    const [amount, setAmount] = useState(0);
    const [currentPrice, setCurrentPrice] = useState(0);
    const [totalPrice, setTotalPrice] = useState(0);
    const [stockList, setStockList] = useState([]);
    const [chartConfig, setChartConfig] = useState({});

    useEffect(() => {
        async function setStocks() {
            const stocks = await fetchChartList();
            setStockList(stocks);
        }

        setStocks();
    }, [stockName])

    return (
        <Inner>
            <Chart>
                <ChartInfo>
                    <ChartTitle>{curStockName}</ChartTitle>
                    <ChartTitle>{currentPrice!==0?currentPrice.toLocaleString("ko-KR")+"원":""}</ChartTitle>
                </ChartInfo>
                <HighChart configs={chartConfig} />
            {
                curStockName!==''?(
                <Trade>
                    <TradeToggle>
                        <ToggleBtn
                            color={type === "buy" ? "red" : "grey"}
                            value={"buy"}
                            onClick={(e) => {
                                e.preventDefault();
                                setType(e.target.value);
                            }}
                        >
                            매수
                        </ToggleBtn>
                        <ToggleBtn
                            color={type === "sell" ? "blue" : "grey"}
                            value={"sell"}
                            onClick={(e) => {
                                e.preventDefault();
                                setType(e.target.value);
                            }}
                        >
                            매도
                        </ToggleBtn>
                    </TradeToggle>

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
                
                    <label>총액</label>
                    <span>{totalPrice.toLocaleString("ko-KR")}</span>
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
                
                    </Trade>
                    ):(<></>)
            }
            </Chart>
            
            <ChartList>
                <ChartListHead>
                    <ChartListInput
                        onChange={(e)=>setStockName(e.target.value)}
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
                                    color={(curStockName===stock.stockName)}
                                    onClick={async (e) => {
                                        setCurrentPrice(await fetchCurPrice(stock.code));
                                        setCurStockName(stock.stockName);
                                        setCurStockCode(stock.code);
                                        setAmount(0);
                                        setTotalPrice(0);
                                        const chartData=await fetchChartData(stock.stockName);
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