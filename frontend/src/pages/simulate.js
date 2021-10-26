import React from "react";
import { useHistory } from "react-router";
import Navigator from "../components/Navigator";
import SimulateContainer from "../components/SimulateContainer";

export default function Simulate() {
    const history = useHistory();
    return (
        <>
            <Navigator history={history} />
            <SimulateContainer history={history}/>
        </>
    )
}