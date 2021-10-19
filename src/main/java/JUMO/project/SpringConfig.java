package JUMO.project;

import JUMO.project.Repository.PriceRepository;
import JUMO.project.Repository.PriceRepositoryImpl;
import JUMO.project.Service.PriceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
//@PropertySource("/predict.properties")
public class SpringConfig{

    private EntityManager entityManager;

    public SpringConfig(EntityManager entityManager){
        this.entityManager=entityManager;
    }

//    @Bean
//    public User_Service user_service(){
//        return new User_Service(user_repository());
//    }

//    @Bean
//    public UserRepository user_repository(){
//        return new User_JPA_Repository(entityManager);
//    }

    @Bean
    public PriceService price_service(){
        return new PriceService(priceRepository());
    }

    @Bean
    public PriceRepository priceRepository(){
        return new PriceRepositoryImpl(entityManager);
    }

//    @Bean(name = "system")
//    public PropertiesFactoryBean propertiesFactoryBean() throws Exception {
//        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
//        ClassPathResource classPathResource = new ClassPathResource("predict.properties");
//
//        propertiesFactoryBean.setLocation(classPathResource);
//
//        return propertiesFactoryBean;
//    }


}

