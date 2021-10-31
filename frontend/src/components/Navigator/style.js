import styled from "styled-components";

export const Header = styled.header`
    width: 100%;
    height: 80px;
    position: fixed;
    top: 0;
    z-index: 9;
    background-color: #f4f5f8;
    border-bottom: 1px solid #c8c8c8;
`

export const Logo = styled.a`
    width: 75px;
    height: 75px;
    position: absolute;
    top: 0;
    left: 0;
    margin: auto;
    background-image: url("/JUMO_logo.png");
    background-repeat: no-repeat;
    background-size:contain;

    &:hover{
        cursor: pointer;
    }
`

export const SubMenu = styled.div`
    height: 80px;
    position: absolute;
    top: 10px;
    left: 85px;
    display: flex;
    top: 0;
    bottom: 0;
    margin: auto;
`

export const Menu = styled.ul`
    font-family: Arial, sans-serif;
    display: flex;
`
export const MenuDetail = styled.li`
    position: relative;
    align-items: center;
    display: flex;
`

export const MenuAnchor = styled.a`
    padding: 11px 16px;
    display: block;
    color: #656565;
    font-size: 14px;
    cursor: pointer;
    &:hover{
        color: #000;
    }
`

export const Empty = styled.div`
    height: 80px;
`
