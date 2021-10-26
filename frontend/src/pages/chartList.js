import React from "react";
import { useHistory } from "react-router";
import ChartListContainer from "../components/ChartListContainer";
import Navigator from "../components/Navigator";


export default function ChartList() {
    const history=useHistory();
    return (
        <>
            <Navigator history={history} />
            <ChartListContainer history={history}/>
        </>
    )
}