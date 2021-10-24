package JUMO.project.springsecurity;

import JUMO.project.Entity.User;
import JUMO.project.Service.UserServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * 참고 https://webfirewood.tistory.com/115
 */
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private Key key;

    private final UserServiceImpl userService;

    @PostConstruct
    protected void init(){
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public Optional<String> createJwtAuthToken(String id, User user){


        Map<String, Object> payloads = new HashMap<>();
        Date now = new Date();
        payloads.put("uid", user.getUid());
        payloads.put("id", user.getId());



        // 토큰 만료 30 분
        long tokenValidTime = 30 * 60 * 1000L;
        String token = Jwts.builder()
                .setClaims(payloads)
                .setSubject(id)
                .signWith(key)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .compact();

        return Optional.of(token);
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userService.loadUserByUsername(this.getUserId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String getUserId(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("id", String.class);
    }

    // Request의 Header에서 token 값을 가져옵니다. "X-AUTH-TOKEN" : "TOKEN값'
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
