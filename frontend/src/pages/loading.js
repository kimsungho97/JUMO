import React from "react";
import ScaleLoader from "react-spinners/ScaleLoader";
import styled from "styled-components";

const Spinner = () => {
    return <ScaleLoader height="160" width="32" color="royalblue" radius="8" />;
};

const Div = styled.div`
    position: fixed;
    width: 100%;
    height: 100%;
    left: 50%;
    top: 50%;
    right: 50%;
    bottom: 50%;
    
`;


export default function Loading() {
    return (
        <>
            <Div>
                <Spinner />
            </Div>
        </>
    )
}