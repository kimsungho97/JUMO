import styled from "styled-components"


export const Inner = styled.div`
    display: flex;
    flex-direction: row;
    margin-left: 20px;
    width: 800px;
    margin: 20px;
`


export const HistoryInner = styled.div`
    display: flex;
    flex-direction: column;
    position: relative;

    height: 100%;
    width: 100%;
    margin: 10px;
    background-color: #fff;
`


export const HistoryTable = styled.table`
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



export const TableData = styled.td`
    width: 150px;
    padding: 10px;
    vertical-align: top;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
`