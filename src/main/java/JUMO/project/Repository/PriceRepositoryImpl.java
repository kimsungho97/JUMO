package JUMO.project.Repository;

import JUMO.project.Entity.Price;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;

@Repository
public class PriceRepositoryImpl implements PriceRepository {
    private final EntityManager entityManager;

    public PriceRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Price> findAll(){
        return entityManager.createQuery("select p from Price p",Price.class).getResultList();
    }

    @Override
    public List<Price> findByName(String name){
        return entityManager.createQuery("select p from Price p where p.id.name like :stockName",Price.class)
                .setParameter("stockName", "%"+name+"%")
                .getResultList();
    }

    @Override
    public List<Price> findByCode(String code){
        return entityManager.createQuery("select p from Price p where p.code like '%"+code+"%'",Price.class)
                .getResultList();
    }

    @Override
    public List<StockInfoDTO> findAllStockInfo(){
        List<StockInfoDTO> prices=entityManager
                .createQuery(
                        "select distinct new JUMO.project.Repository.StockInfoDTO(p.code, p.id.name) from Price p"
                        , StockInfoDTO.class).getResultList();
        return prices;
    }
}
