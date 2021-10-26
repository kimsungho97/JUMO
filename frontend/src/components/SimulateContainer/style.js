import styled from "styled-components";

export const TabBar = styled.div`
    width: 100%;
    height: 40px;
    display: flex;
    flex-direction: row;
    justify-content: start;
    margin-left: 20px;
    margin-top: 20px;
`

export const Tab = styled.div`
    position: relative;
    display: flex;
    flex-direction: column;
    justify-content: center;
    width: 100px;
    height: 100%;
    background-color: ${props=>props.color?"royalblue":"#97BEF1"};
    border-top-left-radius: 10px;
    border-top-right-radius: 10px;
    align-items: center;
    margin-left: 10px;
    &:first-child{
        margin-left: 0px;
    }
`

export const TabSpan = styled.span`
    
`