package mate.academy.internet.shop.dao;

import java.util.List;
import mate.academy.internet.shop.model.Order;
import mate.academy.internet.shop.model.User;

public interface OrderDao extends GenericDao<Order, Long> {

    List<Order> getUserOrders(User user);
}
