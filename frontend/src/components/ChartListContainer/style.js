import styled from "styled-components";

export const SearchBar = styled.div`
    margin-top:20px;
    margin-left:20px;
    font-family: 'Nanum Gothic';
    font-size: 20px;
    font-weight: 400;
`

export const SearchBarlbl = styled.label`
    width: 50px;
    height: 20px;
    margin: 10px 7px 10px 10px;
`

export const SearchBarInput = styled.input`
    font-family: 'Nanum Gothic';
    font-size: 20px;
    font-weight: 400;
`
export const StockTable = styled.table`
    position: absolute;
    margin-top: 10px;
    margin-left: 50px;
    border-collapse: collapse;
    text-align: left;
    line-height: 1.5;
    border-left: 1px solid #ccc;
    border-top: 1px solid #ccc;
    margin : 20px 10px;
`

export const StockTableHeader = styled.th`
    width: 150px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    color: #153d73;
    border-bottom: 3px solid #369;
    border-right: 1px solid #ccc;
`

export const StockTableRow = styled.tr`
    display: ${props=> props.display||"block"};
`

export const StockTableData = styled.td`
    width: 150px;
    padding: 10px;
    vertical-align: top;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
`

export const ChartLinker = styled.a`
    &:hover{
        cursor: pointer;
    }
`