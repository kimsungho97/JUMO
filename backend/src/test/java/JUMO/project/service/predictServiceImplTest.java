package JUMO.project.service;


import JUMO.project.Service.PredictService;
import JUMO.project.Service.PredictServiceImpl;
import org.junit.jupiter.api.Test;

public class predictServiceImplTest {

    PredictService predictService = new PredictServiceImpl();

    @Test
    void getPredictTest(){
        Object st = predictService.getRecommend("호전실업");

    }
}
