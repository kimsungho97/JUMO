package JUMO.project.Service;

import JUMO.project.Entity.Price;
import JUMO.project.Repository.Price_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class Price_Service {
    private final Price_Repository price_service;

    @Autowired
    public Price_Service(Price_Repository price_service) {
        this.price_service = price_service;
    }

    public List<Price> findAll(){
        return price_service.findAll();
    }

    public List<Price> findByname(String name){
        return price_service.findByname(name);
    }

    public List<Price> findBycode(String code){
        return price_service.findBycode(code);
    }
}
