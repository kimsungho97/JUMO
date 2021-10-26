import React from "react";
import LinkTo from "../../hooks/useLink";
import { BtnWrapper, FormHead, FormHeadLogo, FormRow, Input, Btn, LoginForm } from "./style";

export default function LoginContainer({history}) {
    return (
        <LoginForm>
            <FormHeadLogo onClick={(e) => LinkTo(e, history, "/")}>JUMO</FormHeadLogo>
            <FormHead>로그인</FormHead>
            <FormRow>
                <Input
                    type={"text"}
                    name={"id"}
                    placeholder={"아이디"}
                    required=""
                    autoFocus=""
                />
            </FormRow>
            <FormRow>
                <Input
                    type={"text"}
                    name={"password"}
                    placeholder={"비밀번호"}
                    required=""
                />
            </FormRow>
            <BtnWrapper>
                <Btn backgroundColor={"royalblue"}>
                    로그인
                </Btn>
            </BtnWrapper>
            <BtnWrapper>
                <Btn backgroundColor={"#E2E2E2"} onClick={(e) => LinkTo(e, history, "/signup")}>
                    회원가입
                </Btn>
            </BtnWrapper>
        </LoginForm>
    )
}
