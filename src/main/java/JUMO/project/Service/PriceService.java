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
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
public class PriceService {
    private final PriceRepository priceRepository;

    @Autowired
    public PriceService(PriceRepository priceRepository) {
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

    public Map<String, String> allStockName(String name) {
        List<StockInfoDTO> findStocks = priceRepository.findAllStockInfo();

        return findStocks.stream().filter(stockInfoDTO -> stockInfoDTO.getStockName().contains(name))
                .collect(Collectors.toMap(StockInfoDTO::getStockName, StockInfoDTO::getStockId));
    }

    public Map<String, String> allStockCode(String code) {
        List<StockInfoDTO> findStocks = priceRepository.findAllStockInfo();

        HashMap<String,String> result=new HashMap<>();

        return findStocks.stream().filter(stockInfoDTO -> stockInfoDTO.getStockId().contains(code))
                .collect(Collectors.toMap(StockInfoDTO::getStockName, StockInfoDTO::getStockId));

    }
}
