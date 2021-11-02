import styled from "styled-components";

export const Inner = styled.div`
    display: flex;
    flex-direction: row;
    margin-left: 20px;
    
`


export const Info = styled.div`
    display: flex;
    flex-direction: column;
    position: relative;

    height: 80%;
    width: 20%;
    min-height: 500px;
    min-width: 300px;
    border: 1px #fff solid;
`


export const InfoInner = styled.div`
    display: flex;
    flex-direction: column;
    position: relative;
    height: 80%;
    width: 100%;
    min-height: 500px;
    border: 1px #fff solid;
`

export const Holding = styled.div`
    display: flex;
    flex-direction: column;
    position: relative;

    height: 80%;
    width: 70%;
    min-height: 500px;
    min-width: 300px;
    border: 1px #fff solid;
`
export const HoldingInner = styled.div`
    display: flex;
    flex-direction: column;
    position: relative;

    height: 80%;
    width: 100%;
    min-height: 500px;
    border: 1px #fff solid;
`


export const HoldingTable = styled.table`
    display: flex;
    flex-direction: column;
    position: relative;

    height: 80%;
    width: 100%;
     
    
    border-collapse: collapse;
    text-align: left;
    line-height: 1.5;
    border-left: 1px solid #ccc;
    border-top: 1px solid #ccc;
`

export const TableHeader = styled.th`
    width: ${props=>props.width}}px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    color: #153d73;
    border-bottom: 3px solid #369;
    border-right: 1px solid #ccc;
`


export const Title = styled.div`
    position: relative;
    width: 100%;
    heigth: 50px;
    background-color: grey;
    text-align: center;
`

export const TableData = styled.td`
    width: 150px;
    padding: 10px;
    vertical-align: top;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
`