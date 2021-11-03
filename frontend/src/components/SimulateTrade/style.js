import styled from "styled-components";

export const Inner = styled.div`
    position: relative;
    display: flex;
    overflow: hidden;
    justify-content: space-around;
    width: 100%;
    height: 100%;
    border: 2px solid #fff;
    flex-direction: row;

    
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
    padding: 7px;
    display: flex;
    right: 40px;
    flex-direction: column;
    justify-content: start;
    width: 200px;
    height: 80%;
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
    padding-left: 3px;
    background-color: #fff;
    line-height: 26px;
    border: 1px solid #d0d0d5;
    border-radius: 10px;
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
        background-color: royalblue;
    }
    &::-webkit-scrollbar-track {
        background-color: #BFDFFF;
    }
  }
`

export const ChartListData = styled.div`
    display: ${props => props.display};
    background-color: ${props=> props.color?"grey":"none"};
    flex-direction: column;
    justify-content: space-around;
    
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
    font-weight: ${props=>props.fontWeight};
    font-size: ${props => props.fontSize}px;
    font-height: 100%;
    font-family: Noto Sans KR,sans-serif;
`

export const Trade = styled.div`
    position: relative;
    display: flex;
    flex-direction: column;
    width: 400px;
    height: 100%;
    text-align: center;
    align-items: center;
    background-color: #fff;
`

export const AmountInput = styled.input`
    padding: 0 14px;
    padding-right: 20px;
    border: 1px solid #dfe0e5;
    border-radius: 0.1em 0 0 0.1em;
    line-height: 36px;
    color: #2b2b2b;
    font-size: 14px;
    letter-spacing: 1px;
    text-align: right;
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
    appearance: auto;
    -webkit-rtl-ordering: logical;
    cursor: text;
    &::-webkit-inner-spin-button {
        -webkit-appearance: none;
    }
}
`

export const TradeToggle = styled.div`
    width: 100%;
    margin-bottom: 10px;
    display: flex;
    flex-direction: row;
`

export const ToggleBtn = styled.button`
    position: relative;
    width: 50%;
    height: 40px;
    
    background-color: #fff;
    border: none;
    border-bottom: 3px ${props => props.color} solid;
    color: ${props => props.color};
    font-size: 20px;
    font-weight: 700;
    font-family: Noto Sans KR,sans-serif;
    text-align: center;
    &:hover{
        cursor: pointer;
    }
`

export const OrderRow = styled.div`
    position: relative;
    width: 100%;
    height: 50px;
    display: flex;
    padding: 7px;
    flex-direction: row;
    justify-content: space-between;
`

export const OrderBtn = styled.button`
    border: 0;
    background-color: #115dcb;
    width: 100%;
    height: 44px;
    line-height: 44px;
    color: #fff;
    font-size: 14px;
    font-weight: 700;
    font-family: Noto Sans KR,sans-serif;
    text-align: center;
    overflow: hidden;
    &:hover{
        cursor: pointer;
    }
    
`