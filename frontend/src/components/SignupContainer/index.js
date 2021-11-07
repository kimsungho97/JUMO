import React from "react"
import { useRef } from "react";
import LinkTo from "../../hooks/useLink"
import { fetchSignup } from "../../hooks/useLogin";
import { Btn, BtnWrapper, FormHead, FormHeadLogo, FormRow, Input, SignupForm } from "./style"

export default function SignupContainer({ history }) {
    const idRef = useRef(null);
    const pwRef = useRef(null);
    return (
        <SignupForm>
            <FormHeadLogo onClick={(e) => LinkTo(e, history, "/")}>JUMO</FormHeadLogo>
            <FormHead>회원가입</FormHead>
            <FormRow>
                <Input
                    ref={idRef}
                    type={"text"}
                    name={"id"}
                    placeholder={"아이디"}
                    required=""
                    autoFocus=""
                />
            </FormRow>
            <FormRow>
                <Input
                    ref={pwRef}
                    type={"text"}
                    name={"password"}
                    placeholder={"비밀번호"}
                    required=""
                />
            </FormRow>
            <BtnWrapper>
                <Btn backgroundColor={"royalblue"}
                    onClick={async(e) => {
                        e.preventDefault();
                        const result=await fetchSignup(idRef.current.value, pwRef.current.value);
                        if (result.result === true) {
                            alert("회원가입 되셨습니다.")
                            LinkTo(e, history, "/login");
                        }
                        else {
                            alert(result.error);
                        }
                    }}
                >
                    회원가입
                </Btn>
            </BtnWrapper>
        </SignupForm>
    )
}