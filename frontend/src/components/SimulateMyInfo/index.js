import React, { useEffect, useState } from "react";
import { useRecoilValue } from "recoil";
import { fetchHolding, fetchTotalAsset } from "../../hooks/useMyInfo";
import { Loading } from "../../pages";
import { userAtom } from "../../store/user";
import { Holding, HoldingInner, HoldingTable, Info, InfoContent, InfoInner, InfoRow, InfoSpan, Inner, TableData, TableHeader, Title } from "./style";

export default function SimulateMyInfo() {
    const [holdings, setHoldings] = useState([]);
    const userInfo = useRecoilValue(userAtom);
    const [myAssets, setMyAssets] = useState({ userId: userInfo.userId, valuationLoss: 0, valuationLossRate: 0, valuationAmount: 0 });
    const [loading, setLoading] = useState(true);

    
    
    useEffect(() => {
        async function getHoldings() {
            const holding = await fetchHolding(userInfo.userId);
            const assets = await fetchTotalAsset();
            setHoldings(holding);
            setMyAssets(assets);
            setLoading(false);
        }
        getHoldings();
    }, [])
    
    
    
    
    if (loading)
        return <Loading />;
    else
        return (
            <Inner>
                <Info>
                    <Title>내 정보</Title>
                    <InfoInner>
                        <InfoRow>
                            <InfoSpan>아이디</InfoSpan>
                            <InfoContent>{myAssets.userId}</InfoContent>
                        </InfoRow>
                        <InfoRow>
                            <InfoSpan>잔액</InfoSpan>
                            <InfoContent>{userInfo.balance.toLocaleString("ko-KR")}</InfoContent>
                        </InfoRow>
                        
                        <InfoRow>
                            <InfoSpan>손익</InfoSpan>
                            <InfoContent
                                color={myAssets.valuationLoss < 0 ? "#d80e35" : "#115dcb"}
                            >
                                {myAssets.valuationLoss.toLocaleString("ko-KR")}
                            </InfoContent>
                        </InfoRow>
                        <InfoRow>
                            <InfoSpan>수익률</InfoSpan>
                            <InfoContent
                                color={myAssets.valuationLossRate < 0 ? "#d80e35" : "#115dcb"}
                            >
                                {myAssets.valuationLossRate.toFixed(2)}%
                            </InfoContent>
                        </InfoRow>
                        <InfoRow>
                            <InfoSpan>평가금액</InfoSpan>
                            <InfoContent
                                color={myAssets.valuationAmount < 0 ? "#d80e35" : "#115dcb"}
                            >
                                {myAssets.valuationAmount.toLocaleString("ko-KR")}
                            </InfoContent>
                        </InfoRow>
                        <InfoRow>
                            <InfoSpan>보유자산</InfoSpan>
                            <InfoContent>
                                {(myAssets.valuationAmount+userInfo.balance).toLocaleString("ko-KR")}
                            </InfoContent>
                        </InfoRow>
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
                                    holdings.map((holding, index) => {
                                        return (
                                            <tr key={index}>
                                                <TableData>
                                                    {holding.name}
                                                </TableData>
                                                <TableData>
                                                    {holding.valuationLoss.toLocaleString("ko-KR")}
                                                </TableData>
                                                <TableData>
                                                    {holding.yield.toFixed(2).toLocaleString("ko-KR")}%
                                                </TableData>
                                                <TableData>
                                                    {holding.remainingAmount.toLocaleString("ko-KR")}
                                                </TableData>
                                                <TableData>
                                                    {holding.valuationAmount.toLocaleString("ko-KR")}
                                                </TableData>
                                                <TableData>
                                                    {holding.averagePurchasePrice.toLocaleString("ko-KR")}
                                                </TableData>
                                                <TableData>
                                                    {holding.currentPrice.toLocaleString("ko-KR")}
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