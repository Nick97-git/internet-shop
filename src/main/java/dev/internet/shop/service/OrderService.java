package dev.internet.shop.service;

import dev.internet.shop.model.Order;
import dev.internet.shop.model.Product;
import dev.internet.shop.model.User;
import java.util.List;

public interface OrderService extends GenericService<Order, Long> {

    Order completeOrder(List<Product> products, User user);

    List<Order> getUserOrders(User user);

    Long getOrderAmount(Order order);
}
