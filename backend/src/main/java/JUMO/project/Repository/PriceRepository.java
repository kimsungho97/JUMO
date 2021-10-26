package JUMO.project.Repository;

import JUMO.project.Entity.Price;

import javax.persistence
        .EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public interface PriceRepository {
    List<Price> findAll();

    List<Price> findByName(String name);

    List<Price> findByCode(String code);

    List<StockInfoDTO> findAllStockInfo();
}
