package JUMO.project.Repository;

import JUMO.project.Entity.Holding;
import JUMO.project.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class HoldingRepositoryImpl implements HoldingRepository {

    private final EntityManager em;

    @Override
    public void save(Holding holding) {
        em.persist(holding);
    }

    @Override
    public List<Holding> findByUserId(Long uid) {
        return em.createQuery("select h from Holding h where h.user.id = :uid", Holding.class)
                .setParameter("uid", uid)
                .getResultList();
    }

    // singleResult 사용시 검색결과 없으면 exception 발생
    @Override
    public List<Holding> findHoldingByUidStockId(Long uid, String stockId) {
        return em.createQuery("select h from Holding h join h.user where h.stockId =: stockId",Holding.class).
                setParameter("stockId", stockId).getResultList();
    }
}
