import styled from "styled-components";

export const ChartView = styled.div`
    width: 100%;
    height: 80%;
    position: relative;
    top: 50px;
`

export const PredictionInfo = styled.div`
    background-color: #ffffff;
    height: 120%;
    box-shadow: 1px 1px 8px #d5d6d6;
    padding: 10px 10px 10px 10px;
`

export const PredictionTitle = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    width: 100%;
    margin-top: 30px;

    &>*{
        padding: 0px 10px 0px 10px;
    }
`

export const Predictionlbl = styled.label`
    text-align: left;
    position: relative;
    font-family: Arial, sans-serif;
    letter-spacing: -1px;
    font-size: ${props=>props.fontSize}px;
    font-weight: ${props=>props.fontWeight};
`

export const PredictionDiv = styled.div`
    position: relative;
    display: flex;
    flex-direction: row;
    margin-bottom: 7px;
    color: black;
    font-size: 30px;
    text-align: left;
    font-weight: 1000;
`

export const PredictionSpan = styled.span`
    color: #666;
`

export const PredictionResult = styled.label`
    width: 100px;
    border-radius: 9px;
    font-size: 30px;
    text-align: center;
    font-weight: 1000;
    position: absolute;
    left: 400px;
`
