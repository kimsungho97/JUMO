import styled from "styled-components";

export const Inner = styled.div`
    display: flex;
    flex-direction: row;
    margin-left: 20px;
    justify-content: space-between;
    width: 70%;
    min-width: 1000px;
`


export const Info = styled.div`
    display: flex;
    flex-direction: column;
    position: relative;

    height: 100%;
    width: 30%;
    min-width: 300px;
    background-color: #fff;
    margin: 10px;
    border-top: 1px;
    solid #f1f1f4;
`


export const InfoInner = styled.div`
    display: flex;
    flex-direction: column;
    position: relative;
    height: 80%;
    width: 100%;
    
`

export const InfoRow = styled.div`
    position: relative;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    position: relative;
    height: 30px;
    width: 90%;
    margin: 10px;
    padding: 10px;
`

export const InfoSpan = styled.span`
    font-size: 20px;
    color: #666;
`
export const InfoContent = styled.span`
    font-weight: 600;
    letter-spacing: 1px;
    font-size: 20px;
    color: ${props=>props.color===undefined?"#000":props.color}
`

export const Holding = styled.div`
    display: flex;
    flex-direction: column;
    position: relative;
    border-top: 1px;
    solid #f1f1f4;
    

    background-color: #fff;
    margin: 10px;
`
export const HoldingInner = styled.div`
    display: flex;
    flex-direction: column;
    position: relative;

    height: 100%;
    width: 100%;
`


export const HoldingTable = styled.table`
    display: flex;
    flex-direction: column;
    position: relative;

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
    heigth: 60px;
    font-size: 20px;
    padding: 10px;
    text-align: center;
    background-color:#f9fafc;
    font-weight: 700;
    clear: both;
    font-family: Noto Sans KR,sans-serif,AppleSDGothicNeo-Regular,Malgun Gothic,Dotum;
`

export const TableData = styled.td`
    width: 150px;
    padding: 10px;
    vertical-align: top;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
`