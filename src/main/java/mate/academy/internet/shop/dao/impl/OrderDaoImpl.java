package mate.academy.internet.shop.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import mate.academy.internet.shop.dao.OrderDao;
import mate.academy.internet.shop.dao.Storage;
import mate.academy.internet.shop.lib.Dao;
import mate.academy.internet.shop.model.Order;
import mate.academy.internet.shop.model.Product;
import mate.academy.internet.shop.model.User;

@Dao
public class OrderDaoImpl implements OrderDao {

    @Override
    public Order create(List<Product> products, User user) {
        Order order = new Order(products, user);
        Storage.addOrder(order);
        return order;
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return Storage.orders.stream()
                .filter(order -> order.getUser().getId().equals(user.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Order> getOrder(Long id) {
        return Storage.orders
                .stream()
                .filter(order -> order.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Order> getAll() {
        return Storage.orders;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.orders.removeIf(order -> order.getId().equals(id));
    }
}