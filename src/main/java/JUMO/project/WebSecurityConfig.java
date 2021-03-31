package JUMO.project;

import JUMO.project.Service.User_Service;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity // 1
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { // 2

    private final User_Service user_service; // 3

    @Override
    public void configure(WebSecurity web) { // 4
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { // 5
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/login","/").permitAll()
                //.anyRequest().authenticated()
                .and()
                .formLogin() // 7
                .loginPage("/login") // 로그인 페이지 링크
                .loginProcessingUrl("/login")
                .usernameParameter("id")
                .passwordParameter("password")
                .defaultSuccessUrl("/")

                //.failureUrl("/login")
                //.defaultSuccessUrl("/") // 로그인 성공 후 리다이렉트 주소

        ;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception { // 9
        auth.userDetailsService(user_service)
                // 해당 서비스(userService)에서는 UserDetailsService를 implements해서
                // loadUserByUsername() 구현해야함 (서비스 참고)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}