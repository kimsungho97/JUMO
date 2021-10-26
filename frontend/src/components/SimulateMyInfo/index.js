import React from "react";
import { StockTable } from "../ChartListContainer/style";
import { Holding, HoldingInner, HoldingTable, Info, InfoInner, Inner, TableData, TableHeader, Title } from "./style";

export default function SimulateMyInfo() {
    const holdings = fetchHolding("id");



    return (
        <Inner>
            <Info>
                <Title>내 정보</Title>
                <InfoInner>
                    <span>아이디 : ~~~</span>
                    <span>순자산: ~~~</span>
                    <span>손익: ~~~</span>
                    <span>수익률: ~~~</span>
                    <span>평가금액: ~~~</span>
                </InfoInner>
            </Info>

            <Holding>
                <Title>보유 내역</Title>
                <HoldingInner>
                    <HoldingTable>
                        <tbody>
                            <tr>
                                <TableHeader width={150}>
                                    종목명
                                </TableHeader>
                                <TableHeader width={150}>
                                    평가손익
                                </TableHeader>
                                <TableHeader width={100}>
                                    수익률
                                </TableHeader>
                                <TableHeader width={150}>
                                    잔고수량
                                </TableHeader>
                                <TableHeader width={150}>
                                    평가금액
                                </TableHeader>
                                <TableHeader width={150}>
                                    평균매입가
                                </TableHeader>
                                <TableHeader width={170}>
                                    현재가
                                </TableHeader>
                            </tr>

                            {
                                holdings.map((holding) => {
                                    return (
                                        <tr>
                                            <TableData>
                                                {holding["name"]}
                                            </TableData>
                                            <TableData>
                                                {holding["valuation loss"].toLocaleString("ko-KR")}
                                            </TableData>
                                            <TableData>
                                                {holding["yield"].toLocaleString("ko-KR")}%
                                            </TableData>
                                            <TableData>
                                                {holding["remaining amount"].toLocaleString("ko-KR")}
                                            </TableData>
                                            <TableData>
                                                {holding["valuation amount"].toLocaleString("ko-KR")}
                                            </TableData>
                                            <TableData>
                                                {holding["average purchase price"].toLocaleString("ko-KR")}
                                            </TableData>
                                            <TableData>
                                                {holding["current price"].toLocaleString("ko-KR")}
                                            </TableData>
                                        </tr>
                                    )
                                })
                            }

                        </tbody>
                    </HoldingTable>
                </HoldingInner>
            </Holding>
        </Inner>
    )

}

function fetchHolding(id) {
    return [
        {
            "name": "LG",                       //종목명
            "valuation loss": -168536,          //평가손익
            "yield": -16.5,                     //수익률
            "remaining amount": 9,              //잔고수량
            "valuation amount": 855000,         //평가금액
            "average purchase price": 113500,   //평균매입가
            "current price": 95000              //현재가
        },
         {
            "name": "LX홀딩스",                       //종목명
            "valuation loss": -65865,          //평가손익
            "yield": -64.64,                     //수익률
            "remaining amount": 4,              //잔고수량
            "valuation amount": 35560,         //평가금액
            "average purchase price": 25086,   //평균매입가
            "current price": 8890              //현재가
        }
    ]
}