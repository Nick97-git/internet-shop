package mate.academy.internet.shop.service;

import java.util.List;
import mate.academy.internet.shop.exceptions.DataProcessingException;
import mate.academy.internet.shop.model.Order;
import mate.academy.internet.shop.model.Product;
import mate.academy.internet.shop.model.User;

public interface OrderService extends GenericService<Order, Long> {

    Order completeOrder(List<Product> products, User user) throws DataProcessingException;

    List<Order> getUserOrders(User user);
}
