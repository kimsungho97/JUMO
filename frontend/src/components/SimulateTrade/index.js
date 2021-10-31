import React, { useEffect, useRef, useState } from "react";
import { fetchChartList } from "../../hooks/useChart";
import { fetchTrade } from "../../hooks/useTrade";
import HighChart from "../Chart";

import { AmountInput, Chart, ChartList, ChartListCode, ChartListData, ChartListGroup, ChartListHead, ChartListInput, ChartListName, ChartTitle, Inner, OrderBtn, ToggleBtn, Trade, TradeToggle } from "./style";

export default function SimulateTrade() {
    const [curStockName, setCurStockName] = useState("동화약품");
    const [curStockCode, setCurStockCode] = useState("000020.KS");
    const [stockName, setStockName] = useState("");
    const [type, setType] = useState("buy");
    const [amount, setAmount] = useState(0);
    const [total, setTotal] = useState(1);
    const [currentPrice, setCurrentPrice] = useState(700000);
    const [stockList, setStockList] = useState([]);

    useEffect(() => {
        async function setStocks() {
            const stocks = await fetchChartList();
            setStockList(stocks);
        }
        setStocks();
        document.addEventListener("click", candleColorChange);
        candleColorChange();
        
        return () => {
            document.removeEventListener("click", candleColorChange);
        }
    }, [])

    return (
        <Inner>
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
                                    onClick={(e) => {
                                        setCurStockName(stock.stockName);
                                        setCurStockCode(stock.code);
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

            
            <Chart>
                <ChartTitle>{curStockName}</ChartTitle>
                <HighChart stockName={curStockName} />
            </Chart>
            

            <Trade>
                <TradeToggle>
                    <ToggleBtn
                        color={type==="buy"?"red":"grey"}
                        value={"buy"}
                        onClick={(e) => {
                            e.preventDefault();
                            setType(e.target.value);
                        }}
                    >
                        매수
                    </ToggleBtn>
                    <ToggleBtn
                        color={type==="sell"?"blue":"grey"}
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
                        setAmount(parseInt(e.target.value));
                        setTotal(e.target.value * currentPrice)
                    }}
                />
                
                <label>총액</label>
                <span>{total.toLocaleString("ko-KR")}</span>
                <OrderBtn
                    onClick={(e) => {
                        e.preventDefault();
                        fetchTrade(type, curStockName, curStockCode, amount);
                    }}
                >
                    주문
                </OrderBtn>
                
            </Trade>
        </Inner>
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


const rowFiltered = (stockName, name) => {
    if (stockName.indexOf(name) === -1)
        return "none";
    else
        return "flex";
}