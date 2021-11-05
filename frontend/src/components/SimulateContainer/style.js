import styled from "styled-components";

export const Inner = styled.div`
    position: absolute;
    height: 100%;
    width: 100%;
    min-width: 1800px;
    background-color: #e3e5ec;
`

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
    width: 150px;
    height: 100%;
    font-size: 20px;
    font-weight: 700;
    font-family: Noto Sans KR,sans-serif;
    text-align: center;
    color: ${props=>props.color?"royalblue":"grey"};
    border-bottom: 3px solid ${props=>props.color?"royalblue":"grey"};
    align-items: center;
    &:first-child{
        margin-left: 0px;
    }

    &:hover{
        cursor: pointer;
    }
`

export const TabSpan = styled.span`
    
`