import styled from "styled-components";

export const LoginForm = styled.form`
    height: 400px;
    padding: 15px;
    margin: 0 auto;
`
export const FormHeadLogo = styled.a`
    display: block;
    width: 500px;
    text-align: center;
    margin: 0 auto;
    font-size: 120px;
    font-weight: 700;
    color: royalblue;
    cursor: pointer;
    margin-bottom: 50px;
`

export const FormHead = styled.h3`
    width: 200px;
    color: royalblue;
    font-weight: bold;
    font-size: 18px;
    margin: 0 auto;
    text-align: center;
    margin-bottom: 20px;
`

export const FormRow = styled.p`
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    display: flex;
    width: 500px;
    margin: 0 auto;
    justify-content: center;
`

export const Input = styled.input`
    width: 300px;
    height: 30px;
    margin: 4px;
`

export const BtnWrapper = styled.div`
    margin-top: 20px;
    display: flex;
    justify-content: center;
`


export const Btn = styled.button`
    padding: .5rem 1rem;
    font-size: 1.25rem;
    line-height: 1.5;
    border-radius: .5rem;
    color: #fff;
    background-color: ${props=> props.backgroundColor};
    border-color: blue;
     display: inline-block;
    font-weight: 400;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    width: 300px;
    cursor: pointer;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    border: 1px solid transparent;
    padding: .5rem .75rem;
    font-size: 1rem;
    line-height: 1.25;
    border-radius: .25rem;
    transition: all .15s ease-in-out;
`
