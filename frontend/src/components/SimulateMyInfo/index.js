import React from "react";
import { Holding, HoldingInner, HoldingTable, Info, InfoInner, Inner, TableHeader, Title } from "./style";

export default function SimulateMyInfo() {
    const holding = fetchHolding("id");



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
                                    종목코드
                                </TableHeader>
                                <TableHeader width={100}>
                                    잔고수량
                                </TableHeader>
                                <TableHeader width={150}>
                                    평가금액
                                </TableHeader>
                                <TableHeader width={150}>
                                    매입가
                                </TableHeader>
                                <TableHeader width={150}>
                                    현재가
                                </TableHeader>
                            </tr>
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
            "name": "삼성",
            "code": "0000.KS",
            "count": 5,          //주식 개수
            "evaluation": 800000,//현재가 X count
            "buy": 100000,        //매입가
            "current": 160000   //현재가
        }
    ]
}