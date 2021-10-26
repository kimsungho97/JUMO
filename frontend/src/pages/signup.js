import React from "react";
import { useHistory } from "react-router";
import SignupContainer from "../components/SignupContainer";

export default function Signup() {
    const history = useHistory();
    return (
        <>
            <SignupContainer history={history}/>
        </>
    )
}