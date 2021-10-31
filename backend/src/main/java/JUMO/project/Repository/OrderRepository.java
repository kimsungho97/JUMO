package JUMO.project.Repository;

import JUMO.project.Entity.Order;

import java.util.List;

public interface OrderRepository {
    public void save(Order order);

    public Order findById(Long id);
    public List<Order> findAllHistoryByUid(Long uid);
}
