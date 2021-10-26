import React from "react";
import { useHistory } from "react-router";
import LoginContainer from "../components/LoginContainer";

export default function Login() {
    const history=useHistory();
    return (
        <>
            <LoginContainer history={history}/>
        </>
    )
}