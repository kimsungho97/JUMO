package JUMO.project.Service;

import JUMO.project.Entity.Order;
import JUMO.project.Entity.User;

public interface OrderService {

    void buyingOrder(Long uid, String stockId, String stockName, Integer count);
    void sellingOrder(Long uid, String stockId, String stockName, Integer count);
    Long getStockPrice(String stockId);
}
