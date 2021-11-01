package JUMO.project.Repository;

import JUMO.project.Entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository{

    private final EntityManager em;

    @Override
    public void save(Order order) {
        em.persist(order);
    }

    @Override
    public Order findById(Long id) {

        return em.find(Order.class, id);
    }

    @Override
    public List<Order> findAllHistoryByUid(Long uid) {
        return em.createQuery("select o from Order o where o.user.uid = :uid", Order.class)
                .setParameter("uid", uid).getResultList();
    }

    @Override
    public void deleteAll(Long uid) {
        em.createQuery("delete from Order o where o.user.uid = :uid")
                .setParameter("uid", uid)
                .executeUpdate();
    }
}
