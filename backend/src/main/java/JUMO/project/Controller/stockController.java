package JUMO.project.Controller;


import JUMO.project.Entity.Price;
import JUMO.project.Repository.PriceRepository;
import JUMO.project.Repository.StockInfoDTO;
import JUMO.project.Service.PredictService;
import JUMO.project.Service.PriceService;
import JUMO.project.Service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class stockController {

    private final PredictService predictService;
    private final PriceRepository priceRepository;
    private final PriceService priceService;

    @GetMapping("/chartlist")
    public Map<String, List<StockInfoDTO>> chartList(){
        Map<String, List<StockInfoDTO>> responseMap = new HashMap<>();
        responseMap.put("chartlist", priceRepository.findAllStockInfo());
        return responseMap;
    }

    @GetMapping("/chart")
    public Map<String, Object> getOhlc(@RequestParam(name = "stockname") String stockName){
        log.info("search price history for [{}]",stockName);
        List<Price> priceList = priceService.findByName(stockName);

        List<List<Object>> ohlcRowList = new ArrayList<>();
        for(Price price : priceList){
            ArrayList<Object> tmpList = new ArrayList<>();
            tmpList.add(price.getTime().getYear());
            tmpList.add(price.getTime().getMonthValue());
            tmpList.add(price.getTime().getDayOfMonth());
            tmpList.add(price.getOpen());
            tmpList.add(price.getHigh());
            tmpList.add(price.getLow());
            tmpList.add(price.getClose());
            tmpList.add(price.getAdjClose());
            ohlcRowList.add(tmpList);
        }

        Map<String, Object> resMap = new HashMap<>();
        boolean result = true;
        if (priceList.isEmpty()){
            log.warn("no history such for [{}]", stockName);
            resMap.put("result", false);
        } else{
            resMap.put("result", true);
        }
        resMap.put("data", ohlcRowList);

        return resMap;
    }

    @GetMapping("/prediction")
    public Map<String, Object> getPredict(@RequestParam String stockName){
        ArrayList<String> recommendResult = predictService.getRecommend(stockName);

        Map<String, Object> model = new HashMap<>();
        model.put("data", recommendResult);
        return model;
    }
}
