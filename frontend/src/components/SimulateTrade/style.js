import styled from "styled-components";

export const Inner = styled.div`
    position: relative;
    display: flex;
    overflow: hidden;
    justify-content: space-around;
    width: 100%;
    height: 100%;
    flex-direction: row;

    margin-top: 20px;
    margin-left: 20px;
    
`

export const ChartInfo = styled.div`
    position: relative;
    display: flex;
    flex-direction: column;
    width: 100%;
    height: 100px;
    align-items: left;
`

export const ChartList = styled.div`
    position: relative;
    display: flex;
    right: 40px;
    flex-direction: column;
    justify-content: start;
    width: 200px;
    height: 100%;
    margin-right: 20px;
    border-radius: 10px;
    background-color: #fff;
`

export const ChartListHead = styled.div`
    position: relative;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    width: 100%;
    height: 50px;
`



export const ChartListInput = styled.input`
    position: relative;
    float: left;
    width: 100%;
    height: 40px;
    margin: 0;
    padding: 0;
    background-color: #fff;
    border: 0;
    line-height: 26px;
    color: #2b2b2b;
    font-weight: 700;
    font-family: Dotum,Noto Sans KR,sans-serif;
    font-size: 100%;
    vertical-align: middle;
    -webkit-writing-mode: horizontal-tb !important;
    font-style: ;
    font-variant-ligatures: ;
    font-variant-caps: ;
    font-variant-numeric: ;
    font-variant-east-asian: ;
    font-weight: ;
    font-stretch: ;
    font-size: ;
    font-family: ;
    text-rendering: auto;
    color: -internal-light-dark(black, white);
    letter-spacing: normal;
    word-spacing: normal;
    line-height: normal;
    text-transform: none;
    text-indent: 0px;
    text-shadow: none;
    display: inline-block;
    text-align: start;
    appearance: auto;
    -webkit-rtl-ordering: logical;
    cursor: text;
`

export const ChartListGroup = styled.div`
    display: flex;
    height: 90%;
    flex-direction: column;
    justify-content: start;
    overflow: auto;
    &::-webkit-scrollbar{
        width: 10px;
        background-color: black;
    }
    &::-webkit-scrollbar-thumb {
        background-color: #2f3542;
    }
    &::-webkit-scrollbar-track {
        background-color: grey;
    }
  }
`

export const ChartListData = styled.div`
    display: ${props => props.display};
    background-color: ${props=> props.color?"grey":"none"};
    flex-direction: column;
    justify-content: space-around;
    border-top: 1px solid grey;
    border-bottom: 1px solid grey;
    &:hover{
        background-color: grey;
        cursor: pointer;
    }
`

export const ChartListName = styled.span`
    margin: 5px;
    font-weight: bold;
`
export const ChartListCode = styled.span`
    margin: 3px;
    font-weight: 300;
`

export const Chart = styled.div`
    position: relative;
    width: 80%;
    min-width: 700px;
    height: 100%;
    text-align: center;
    margin-left: 3%;
    margin-right: 3%;
`

export const ChartTitle = styled.div`
    height: 50px;
    width: 70%;
    text-align: left;
    padding-top: 10px;
    padding-left: 10px;
    font-size: 20px;
    font-family: Noto Sans KR,sans-serif;
`

export const Trade = styled.div`
    position: relative;
    display: flex;
    flex-direction: column;
    width: 400px;
    height: 100%;
    text-align: center;
    background-color: #fff;
`

export const AmountInput = styled.input`

`

export const TradeToggle = styled.div`
    width: 200px;
    height: 80px;
    display: flex;
    flex-direction: row;
`

export const ToggleBtn = styled.button`
    position: relative;
    width: 50%;
    height: 100%;
    background-color: ${props => props.color};
    &:hover{
        cursor: pointer;
    }
`

export const OrderBtn = styled.button`
    width: 50px;
    height: 50px;
    &:hover{
        cursor: pointer;
    }
    
`