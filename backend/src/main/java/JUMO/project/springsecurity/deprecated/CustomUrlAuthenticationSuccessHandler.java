package JUMO.project.springsecurity.deprecated;

import JUMO.project.Entity.User;
import JUMO.project.Repository.UserRepository;
import JUMO.project.springsecurity.JsonResultDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class CustomUrlAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if (authentication != null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal != null && principal instanceof UserDetails) {
                Optional<User> findUser = userRepository.findById(((UserDetails) principal).getUsername());
                user = findUser.get();
                log.info(user.toString());
            }
        }
        Optional<String> token = createJwtAuthToken(user.getId(), 10000L, user);
        log.info(token.get());

        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        MediaType jsonMimeType = MediaType.APPLICATION_JSON;

        JsonResultDTO jsonResult = new JsonResultDTO(true, user.getId(), user.getBalance(), null);
        if (jsonConverter.canWrite(jsonResult.getClass(), jsonMimeType)) {
            jsonConverter.write(jsonResult, jsonMimeType, new ServletServerHttpResponse(response));
        }
    }



    public void setRequestCache(RequestCache requestCache){
        this.requestCache = requestCache;
    }


    private Optional<String> createJwtAuthToken(String id, Long expiredMinutes, User user){
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        long expired = expiredMinutes * 60L;

        Map<String, Object> payloads = new HashMap<>();
        Date now = new Date();
        now.setTime(now.getTime() + expired);
        payloads.put("uid", user.getUid());
        payloads.put("id", user.getId());


        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        String token = Jwts.builder()
                .setHeader(headers)
                .setClaims(payloads)
                .setSubject(id)
                .signWith(key)
                .compact();

        return Optional.of(token);

    }
}
