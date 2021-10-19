package JUMO.project.Service;

import JUMO.project.Entity.Price;
import JUMO.project.Repository.PriceRepository;
import JUMO.project.Repository.StockInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Transactional
@Service
public class Price_Service {
    private final PriceRepository priceRepository;

    @Autowired
    public Price_Service(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public List<Price> findAll(){
        return priceRepository.findAll();
    }

    public List<Price> findByName(String name){
        return priceRepository.findByName(name);
    }

    public List<Price> findByCode(String code){
        return priceRepository.findByCode(code);
    }

    public HashMap<String,String> allStockName(String name) {
        List<StockInfoDTO> findStocks = priceRepository.findAllStockInfo();

        HashMap<String,String> result=new HashMap<>();
        for (StockInfoDTO stock : findStocks) {
            if(stock.getStockName().contains(name)){
                result.put(stock.getStockName(), stock.getStockId());
            }
        }

        return result;
    }
    public HashMap<String,String> allStock_code(String code) {
        List<StockInfoDTO> findStocks = priceRepository.findAllStockInfo();

        HashMap<String,String> result=new HashMap<>();
        for (StockInfoDTO stock : findStocks) {
            if(stock.getStockId().contains(code)){
                result.put(stock.getStockName(), stock.getStockId());
            }
        }

        return result;
    }
}
