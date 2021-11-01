import React from "react";
import { useHistory } from "react-router";
import LoginContainer from "../components/LoginContainer";
import MyPageContainer from "../components/MyPageContainer";
import Navigator from "../components/Navigator";

export default function MyPage() {
    const history = useHistory();
    return (
        <>
            <Navigator history={history} />
            <MyPageContainer/>
        </>
    )
}