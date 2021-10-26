import React from "react";
import { useHistory } from "react-router";
import MainContainer from "../components/MainContainer";
import Navigator from "../components/Navigator";

export default function Home() {
    const history = useHistory();
    return (
        <>
            <Navigator history={history}/>
            <MainContainer history={history}/>
        </>
    )
}