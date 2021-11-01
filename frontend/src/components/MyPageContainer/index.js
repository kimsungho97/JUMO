import React from "react";
import { useRecoilState } from "recoil";
import { fetchReset, fetchUserInfo } from "../../hooks/useMyInfo";
import { userAtom } from "../../store/user";
import { Inner, MyInfoDiv, MyInfoRow } from "./style";

export default function MyPageContainer() {
    const [userInfo, setUserInfo] = useRecoilState(userAtom);
    
    return (
        <Inner>
            <MyInfoDiv>
                <MyInfoRow>
                    ID: {userInfo.userId}
                </MyInfoRow>
                <MyInfoRow>
                    남은 잔액: {userInfo.balance.toLocaleString("ko-KR")}
                </MyInfoRow>
                <button
                    onClick={async (e) => {
                        e.preventDefault();
                        const result = await fetchReset();
                        if(result!==200){
                            alert("초기화 오류!");
                        }
                        else {
                            alert("초기화 완료!");
                            const userData = await fetchUserInfo();
                            setUserInfo({ userId: userData.id, balance: userData.balance });                        }
                    }}
                >
                    초기화버튼^^
                </button>
            </MyInfoDiv>
        </Inner>
    )
}