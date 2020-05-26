package dev.internet.shop.dao;

import dev.internet.shop.model.Order;
import dev.internet.shop.model.User;
import java.util.List;

public interface OrderDao extends GenericDao<Order, Long> {

    List<Order> getUserOrders(User user);
}
