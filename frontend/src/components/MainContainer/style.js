import styled from "styled-components";

export const ViewPrice = styled.div`
    height: 400px;
    text-align: center;
    position: relative;

    &::after{
        content:"";
        width: 100%;
        height: 100%;
        background-image: url('/Mar-Business_6.jpg');
        background-repeat: no-repeat;
        background-size: cover;
        opacity: .3;
        background-position: center;
        position: absolute;
        z-index: -1;
        top: 0;
        left: 0;
    }
`

export const ExplainPrice = styled.h1`
    width: 100%;
    position: absolute;
    display: block;
    top: 100px;
    font-family: Arial, sans-serif;
    letter-spacing: -1px;
    font-size: 32px;
    font-weight: 400;
`

export const SubExplain = styled.span`
    position: absolute;
    display: block;
    width: 100%;
    top:150px;
`

export const SubInfo = styled.div`
    width: 300px;
    height: 100px;
    margin: 0 auto;
    top: ${props=> props.top}px;
    position: relative;
    z-index: 2;
    display: flex;
    flex-wrap: wrap;
    align-content: center;
    justify-content: center;
`

export const CheckPrice = styled.div`
    width: 100px;
    height: 100px;
    position: relative;
    z-index: 3;
`
export const CheckPriceBtn = styled.a`
    position: absolute;
    width: 100px;
    height: 50px;
    z-index: 3;
    background-color: royalblue;
    border: 1px solid rgb(55, 103, 233);
    text-align: center;
    font-size: 14px;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-content: center;
    border-radius: 11px;
    top: 0;
    bottom: 0;
    margin: auto;
    transition: background-color 0.3s;
    color: white;
    cursor: pointer;
    &:hover{
        background-color: #f4f5f8;
        color: #333;
    }
`

export const ViewChart = styled.div`
    height: 200px;
    text-align: center;
    position: relative;

    &::after{
        content:"";
        width: 100%;
        height: 100%;
        background-image: url('/chart_background.jpg');
        background-attachment: fixed;
        background-size: cover;
        background-position: center;
        position: absolute;
        z-index: -1;
        top: 0;
        left: 0;
    }
`

export const ExplainChart = styled.h1`
    width: 100%;
    position: absolute;
    display: block;
    top: 40px;
    font-family: Arial, sans-serif;
    font-size: 32px;
    font-weight: 400;
    color: white;
    text-shadow: #000 5px 5px 5px;
`

export const CheckChartBtn = styled.a`
    position: absolute;
    width: 100px;
    height: 50px;
    z-index: 3;
    background-color: rgba(255,255,255,0.4);
    border: 1px solid black;
    text-align: center;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-content: center;
    border-radius: 11px;
    top: 0;
    bottom: 0;
    margin: auto;
    transition: background-color 0.3s;
    color: white;
    text-shadow: #000 1px 1px 1px;
    cursor: pointer;
    &:hover{
        background-color: #f4f5f8;
        color: #333;
        text-shadow: none;
    }
`