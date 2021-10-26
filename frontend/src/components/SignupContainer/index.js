import React from "react"
import LinkTo from "../../hooks/useLink"
import { Btn, BtnWrapper, FormHead, FormHeadLogo, FormRow, Input, SignupForm } from "./style"

export default function SignupContainer({history}) {
    return (
        <SignupForm>
            <FormHeadLogo onClick={(e) => LinkTo(e, history, "/")}>JUMO</FormHeadLogo>
            <FormHead>회원가입</FormHead>
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
        </SignupForm>
    )
}