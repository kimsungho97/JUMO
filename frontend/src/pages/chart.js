import React from "react";
import { useHistory } from "react-router";
import ChartContainer from "../components/ChartContainer";
import Navigator from "../components/Navigator";


export default function Chart() {
    const history=useHistory();
    return (
        <>
            <Navigator history={history} />
            <ChartContainer history={history}/>
        </>
    )
}