import React, { useRef } from "react";
import LinkTo from "../../hooks/useLink";
import { BtnWrapper, FormHead, FormHeadLogo, FormRow, Input, Btn, LoginForm } from "./style";
import { fetchLogin } from "../../hooks/useLogin";
import { useRecoilValue, useSetRecoilState } from "recoil";
import { userAtom } from "../../store/user";
import { setCookie } from "../../hooks/cookie";
import axios from "axios";

export default function LoginContainer({ history }) {
    const setUser = useSetRecoilState(userAtom);
    const idInput = useRef(null);
    const pwInput = useRef(null);

    return (
        <LoginForm>
            <FormHeadLogo onClick={(e) => LinkTo(e, history, "/")}>JUMO</FormHeadLogo>
            <FormHead>로그인</FormHead>
            <FormRow>
                <Input
                    ref={idInput}
                    type={"text"}
                    name={"id"}
                    placeholder={"아이디"}
                    required=""
                    autoFocus=""
                />
            </FormRow>
            <FormRow>
                <Input
                    ref={pwInput}
                    type={"password"}
                    name={"password"}
                    placeholder={"비밀번호"}
                    required=""
                />
            </FormRow>
            <BtnWrapper>
                <Btn backgroundColor={"royalblue"}
                    onClick={async (event) => {
                        event.preventDefault();
                        const result = await fetchLogin(idInput.current.value, pwInput.current.value);
                        if (result.result)
                            successLogin(result, idInput.current.value, setUser, history);
                        else failLogin();
                    }
                }>
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


function successLogin({balance, token},id ,setUser, history) {
    setUser({ balance, userId: id });
    setCookie("token", token, []);
    axios.defaults.headers.common["X-AUTH-TOKEN"] = token;
    LinkTo(null, history, "/");
}

function failLogin() {
    alert("계정 정보가 일치하지 않습니다.");
}