import React, {useEffect} from "react";
import LinkTo from "../../hooks/useLink";
import { CheckChartBtn,CheckPriceBtn, CheckPrice, ExplainPrice, SubExplain, ViewPrice, SubInfo, ViewChart, ExplainChart } from "./style";


export default function MainContainer({history}) {
    
    return (
        <>
            <ViewPrice>
                <ExplainPrice>
                    주가 예측과 모의투자 JUMO와 함께
                </ExplainPrice>
                <SubExplain>
                    에측과 손쉬운 모의투자 한번에!
                </SubExplain>
                <SubInfo top={200}>
                <CheckPrice>
                    <CheckPriceBtn onClick={(e)=>LinkTo(e,history,"simulate")}>
                        <span>
                            모의투자
                        </span>
                    </CheckPriceBtn>
                </CheckPrice>
                </SubInfo>
            </ViewPrice>

            <ViewChart>
                <ExplainChart>
                    차트 확인도 JUMO와 함께
                </ExplainChart>
                <SubInfo top={80}>
                    <CheckPrice>
                        <CheckChartBtn onClick={(e)=>LinkTo(e,history,"/chartlist")}>
                            <span>
                                차트보기
                            </span>
                        </CheckChartBtn>
                    </CheckPrice>
                </SubInfo>
            </ViewChart>
            
        </>
    )
}