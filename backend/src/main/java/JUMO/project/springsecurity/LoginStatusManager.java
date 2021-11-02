package JUMO.project.springsecurity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

/**
 * user 메모리에 로그인 정보만 기록
 * JwtAuthenticationFilter 에 validate
 * UserController 에 로그인 로그아웃 시 메모리에 저장 및 삭제
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LoginStatusManager {
    private final JwtTokenProvider jwtTokenProvider;


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

    TimerTask loginGarbageCollector = new TimerTask() {
        @Override
        public void run() {
            log.info("Garbage collecting starts");
            for(String token : loginStatusMap.keySet()){
                if(!jwtTokenProvider.validateToken(token)){
                    loginStatusMap.remove(token);
                    log.info("token [{}] has been removed by invalidate", token);
                }
            }

        }
    };
    Timer scheduler = new Timer();

    @PostConstruct
    public void initialize(){
        scheduler.schedule(loginGarbageCollector, 1800000, 1800000);
        log.info("Login garbage collector initialized");
    }
}
