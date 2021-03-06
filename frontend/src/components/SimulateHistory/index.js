import React, { useEffect, useState } from "react";
import { useRecoilValue } from "recoil";
import { fetchHistory } from "../../hooks/useMyInfo";
import { Loading } from "../../pages";
import { userAtom } from "../../store/user";
import { TableData } from "../SimulateMyInfo/style";
import { HistoryInner, HistoryTable, Inner, TableHeader } from "./style";

export default function SimulateHistory() {
    const [histories, setHistories] = useState([]);
    const [loading, setLoading] = useState(true);
    const userInfo = useRecoilValue(userAtom);

    useEffect(() => {
        async function getHistory(userId) {
            const data = await fetchHistory(userId);
            setHistories(data);
            setLoading(false);
        }
        getHistory(userInfo.userId);
    }, [])
    

    if (loading)
        return <Loading />;
    else
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
                                                {history.date}
                                            </TableData>
                                            <TableData>
                                                {history.stockName}
                                            </TableData>
                                            <TableData>
                                                {history.type}
                                            </TableData>
                                            <TableData>
                                                {history.amount.toLocaleString("ko-KR")}
                                            </TableData>
                                            <TableData>
                                                {history.total.toLocaleString("ko-KR")}
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
