package mate.academy.internet.shop.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.internet.shop.model.Order;
import mate.academy.internet.shop.model.Product;
import mate.academy.internet.shop.model.User;

public interface OrderDao {

    Order create(List<Product> products, User user);

    List<Order> getUserOrders(User user);

    Optional<Order> getOrder(Long id);

    List<Order> getAll();

    boolean delete(Long id);
}
