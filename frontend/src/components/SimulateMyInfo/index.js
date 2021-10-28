import React, { useEffect, useState } from "react";
import { useRecoilValue } from "recoil";
import { fetchHolding } from "../../hooks/useMyInfo";
import { userAtom } from "../../store/user";
import { Holding, HoldingInner, HoldingTable, Info, InfoInner, Inner, TableData, TableHeader, Title } from "./style";

export default function SimulateMyInfo() {
    const [holdings, setHoldings] = useState([]);
    const userInfo = useRecoilValue(userAtom);
    
    useEffect(async () => {
        setHoldings(await fetchHolding(userInfo.userId));
    }, [])
    



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