package JUMO.project.Repository;

import JUMO.project.Entity.Order;

public interface OrderRepository {
    public void save(Order order);

    public Order findById(Long id);
}
