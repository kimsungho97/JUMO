import styled from "styled-components";

export const Inner = styled.div`
    display: flex;
    height: 90%;
    flex-direction: row;
    margin-left: 20px;
`

export const ChartList = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: start;
    width: 200px;
    margin-right: 20px;
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

`

export const ChartListGroup = styled.div`
    display: flex;
    height: 70%;
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
    display: ${props=>props.display};
    flex-direction: column;
    justify-content: space-around;
    border-top: 1px solid grey;
    border-bottom: 1px solid grey;
    &:hover{
        background-color: grey;
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
    width: 700px;
    height: 100%;
    text-align: center;
`

export const ChartTitle = styled.div`
    height: 50px;
    width: 100%;
`