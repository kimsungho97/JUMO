//package JUMO.project.service;
//
//import JUMO.project.Service.PredictService;
//import JUMO.project.SpringConfig;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.stereotype.Component;
//
//public class PropertyTest {
//
//    @Value("#{system['predict.URL']}")
//    private String myValue;
//
////    private final PredictService predictService;
//
//    ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
//    @Test
//    void getPropertyTest(){
////        System.out.println(myValue);
////        Assertions.assertThat(myValue).isEqualTo("localhost:5000");
//        System.out.println("add = " + myValue);
//    }
//}
