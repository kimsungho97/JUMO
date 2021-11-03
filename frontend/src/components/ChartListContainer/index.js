import React, {useEffect, useState} from "react";
import { fetchChartList } from "../../hooks/useChart";
import LinkTo from "../../hooks/useLink";
import { Loading } from "../../pages";
import { ChartLinker, SearchBar, SearchBarInput, SearchBarlbl, StockTable, StockTableData, StockTableHeader, StockTableRow } from "./style";

export default function ChartListContainer({ history }) {
    const [stockName, setStockName] = useState('');
    const [stockCode, setStockCode] = useState('');
    const [loading, setLoading] = useState(true);
    const [data, setData] = useState([]);

    useEffect(() => {
        async function setChartList() {
            const chartList = await fetchChartList();
            setData(chartList);
            setLoading(false);
        }
        
        setChartList();
    }, [])
    
    if (loading)
        return <Loading />
    else
        return (
            <>
                <SearchBar>
                    <SearchBarlbl>종목명</SearchBarlbl>
                    <SearchBarInput
                        onChange={(e) => setStockName(e.target.value)}
                        placeholder="종목명 입력"
                    />

                    <SearchBarlbl>종목코드</SearchBarlbl>
                    <SearchBarInput
                        onChange={(e) => setStockCode(e.target.value)}
                        placeholder="종목코드 입력"
                    />
                </SearchBar>

                <StockTable>
                    <tbody>
                        <tr>
                            <StockTableHeader colSpan={1}>
                                종목명
                            </StockTableHeader>
                            <StockTableHeader colSpan={1}>
                                종목 코드
                            </StockTableHeader>
                            <StockTableHeader colSpan={1}>
                                차트
                            </StockTableHeader>
                        </tr>

                        {
                            data.map((stock, index) => {
                                return (
                                    <StockTableRow key={index} display={rowFiltered(stock, stockName, stockCode)}>
                                        <StockTableData>
                                            {stock.stockName}
                                        </StockTableData>
                                        <StockTableData>
                                            {stock.code}
                                        </StockTableData>
                                        <StockTableData>
                                            <ChartLinker onClick={(e) => LinkTo(e, history, `/chart?stockName=${stock.stockName}&stockCode=${stock.code}`)}>
                                                차트보기
                                            </ChartLinker>
                                        </StockTableData>
                                    </StockTableRow>
                                )
                            })
                        }

                    </tbody>
                </StockTable>


            </>
        )
}

const rowFiltered = (stock, name, code) => {
    if (stock.stockName.indexOf(name) === -1 || stock.code.indexOf(code) === -1)
        return "none";
    else
        return "table-row";
}