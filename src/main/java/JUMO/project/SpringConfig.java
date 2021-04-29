package JUMO.project;

import JUMO.project.Repository.Price_JPA_Repository;
import JUMO.project.Repository.User_JPA_Repository;
import JUMO.project.Repository.User_Repository;
import JUMO.project.Service.Price_Service;
import JUMO.project.Service.User_Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig{

    private EntityManager entityManager;

    public SpringConfig(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Bean
    public User_Service user_service(){
        return new User_Service(user_repository());
    }

    @Bean
    public User_Repository user_repository(){
        return new User_JPA_Repository(entityManager);
    }

    @Bean
    public Price_JPA_Repository price_jpa_repository(){
        return new Price_JPA_Repository(entityManager);
    }

    @Bean
    public Price_Service price_service(){
        return new Price_Service(price_jpa_repository());
    }
}
