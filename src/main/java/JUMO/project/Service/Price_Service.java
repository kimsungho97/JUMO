package JUMO.project.Service;

import JUMO.project.Entity.Price;
import JUMO.project.Repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;


@Transactional
@Service
public class Price_Service {
    private final PriceRepository price_service;

    @Autowired
    public Price_Service(PriceRepository price_service) {
        this.price_service = price_service;
    }

    public List<Price> findAll(){
        return price_service.findAll();
    }

    public List<Price> findByName(String name){
        return price_service.findByName(name);
    }

    public List<Price> findByCode(String code){
        return price_service.findByCode(code);
    }

    public HashMap<String,String> allStock_name(String name) {
        return price_service.allStock_name(name);
    }
    public HashMap<String,String> allStock_code(String code) {
        return price_service.allStock_code(code);
    }
}
