import React, { useEffect, useRef, useState } from "react";
import { useRecoilState } from "recoil";
import { fetchChartList } from "../../hooks/useChart";
import { fetchUserInfo } from "../../hooks/useMyInfo";
import { fetchCurPrice, fetchTrade } from "../../hooks/useTrade";
import { userAtom } from "../../store/user";
import HighChart from "../Chart";

import { AmountInput, Chart, ChartList, ChartListCode, ChartListData, ChartListGroup, ChartListHead, ChartListInput, ChartListName, ChartTitle, Inner, OrderBtn, ToggleBtn, Trade, TradeToggle } from "./style";

export default function SimulateTrade() {
    const [userInfo, setUserInfo] = useRecoilState(userAtom);
    const [curStockName, setCurStockName] = useState("");
    const [curStockCode, setCurStockCode] = useState("");
    const [stockName, setStockName] = useState("");
    const [type, setType] = useState("buy");
    const [amount, setAmount] = useState(0);
    const [currentPrice, setCurrentPrice] = useState(0);
    const [totalPrice, setTotalPrice] = useState(0);
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
                                    onClick={async (e) => {
                                        setCurrentPrice(await fetchCurPrice(stock.code));
                                        setCurStockName(stock.stockName);
                                        setCurStockCode(stock.code);
                                        setAmount(0);
                                        setTotalPrice(0);
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