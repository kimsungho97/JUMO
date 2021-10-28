import React from "react";
import { TableData } from "../SimulateMyInfo/style";
import { HistoryInner, HistoryTable, Inner, TableHeader } from "./style";

export default function SimulateHistory() {
    const histories = fetchHistory("id");

    return (
        <Inner>
            <HistoryInner>
                <HistoryTable>
                    <tbody>
                        <tr>
                            <TableHeader width={200}>
                                거래 일자
                            </TableHeader>
                            <TableHeader width={150}>
                                종목명
                            </TableHeader>
                            <TableHeader width={150}>
                                매도/매수
                            </TableHeader>
                            <TableHeader width={200}>
                                체결 수량
                            </TableHeader>
                            <TableHeader width={200}>
                                총 금액
                            </TableHeader>
                        </tr>
                        {
                            histories.map((history, index) => {
                                return (
                                    <tr key={index}>
                                        <TableData>
                                            {history["date"]}
                                        </TableData>
                                        <TableData>
                                            {history["name"]}
                                        </TableData>
                                        <TableData>
                                            {history["type"]}
                                        </TableData>
                                        <TableData>
                                            {history["amount"].toLocaleString("ko-KR")}
                                        </TableData>
                                        <TableData>
                                            {history["total"].toLocaleString("ko-KR")}
                                        </TableData>
                                    </tr>
                                )
                            })
                        }
                    </tbody>
                </HistoryTable>
            </HistoryInner>
        </Inner>
    )    
}


function fetchHistory(id) {
    return [
        {
            "date": "2021-10-26",
            "name": "LG",
            "type": "매수",
            "amount": 9,
            "total": 1200000,   
        },
         {
            "date": "2021-10-26",
            "name": "LG",
            "type": "매도",
            "amount": 9,
            "total": 1200000,   
        }
    ]
}