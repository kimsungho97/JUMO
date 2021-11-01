package JUMO.project.Repository;

import JUMO.project.Entity.Holding;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


public interface HoldingRepository {
    public void save(Holding holding);
    public List<Holding> findByUserId(Long uid);
    public List<Holding> findHoldingByUidStockId(Long uid, String stockId);
    public void deleteAll(Long uid);
    public void deleteHoldingByUidStockCode(Long Uid, String stockId);
}
