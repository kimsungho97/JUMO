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
}
