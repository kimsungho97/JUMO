package JUMO.project.springsecurity;

import JUMO.project.Repository.UserRepository;
import JUMO.project.Service.UserServiceImpl;
import JUMO.project.springsecurity.deprecated.CustomAuthFailureHandler;
import JUMO.project.springsecurity.deprecated.CustomUrlAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@RequiredArgsConstructor
@EnableWebSecurity // 1
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { // 2

    private final UserServiceImpl user_service; // 3

    private final JwtTokenProvider jwtTokenProvider;
    private final LoginStatusManager loginStatusManager;

    private final CustomAuthFailureHandler customAuthFailureHandler;

    private final UserRepository userRepository;

    @Override
    public void configure(WebSecurity web) { // 4
        web.ignoring().antMatchers("/templates/css/**", "/js/**", "/img/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { // 5
        http
                .csrf()
                .disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
//                .antMatchers("/login").hasRole("USER")
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/api/login", "/api/signup").permitAll()
                .anyRequest().hasRole("USER")
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, loginStatusManager),
                        UsernamePasswordAuthenticationFilter.class)
        ;
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(UserRepository userRepository){
        return new CustomUrlAuthenticationSuccessHandler(userRepository);
    }



    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception { // 9
        auth.userDetailsService(user_service)
                // ?????? ?????????(userService)????????? UserDetailsService??? implements??????
                // loadUserByUsername() ??????????????? (????????? ??????)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public CustomAuthFailureHandler authFailureHandler(){
        return new CustomAuthFailureHandler();
    }

}

