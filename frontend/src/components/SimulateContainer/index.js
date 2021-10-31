import React, { useState } from "react";
import SimulateHistory from "../SimulateHistory";
import SimulateMyInfo from "../SimulateMyInfo";
import SimulateTrade from "../SimulateTrade";
import { TabBar, Tab, TabSpan } from "./style";

const tabList = {
    "myInfo": <SimulateMyInfo />,
    "history": <SimulateHistory />,
    "trade": <SimulateTrade/>
}

export default function SimulateContainer({ history }) {
    const [tabMenu, setTabMenu] = useState("myInfo");

    return (
        <>
            <TabBar>
                <Tab
                    color={(tabMenu==="myInfo").toString()}
                    onClick={() => { setTabMenu("myInfo") }}
                >
                    <TabSpan>
                        보유 내역
                    </TabSpan>
                </Tab>
                <Tab
                    color={(tabMenu==="history").toString()}
                    onClick={() => { setTabMenu("history") }}
                >
                    <TabSpan>
                        거래 내역
                    </TabSpan>
                </Tab>
                <Tab
                    color={(tabMenu==="trade").toString()}
                    onClick={() => { setTabMenu("trade") }}
                >
                    <TabSpan>
                        매수/매도
                    </TabSpan>
                </Tab>
            </TabBar>
            
            {tabList[tabMenu]}
        </>
    )

}