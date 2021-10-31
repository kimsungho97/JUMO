package JUMO.project.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * user 메모리에 로그인 정보만 기록
 * JwtAuthenticationFilter 에 validate
 * UserController 에 로그인 로그아웃 시 메모리에 저장 및 삭제
 */
@Component
public class LoginStatusManager {
    private static final ConcurrentHashMap<String, Boolean> loginStatusMap = new ConcurrentHashMap<>();

    public void putLoginStatus(String token){
        loginStatusMap.put(token, Boolean.TRUE);
    }

    public void removeLoginStatus(String token){
        loginStatusMap.remove(token);
    }

    public boolean isTokenInLoginStatus(String token){
        return loginStatusMap.containsKey(token);
    }
}
