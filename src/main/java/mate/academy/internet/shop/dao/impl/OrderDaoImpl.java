package mate.academy.internet.shop.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import mate.academy.internet.shop.dao.OrderDao;
import mate.academy.internet.shop.dao.Storage;
import mate.academy.internet.shop.model.Order;
import mate.academy.internet.shop.model.User;

public class OrderDaoImpl implements OrderDao {

    @Override
    public Order create(Order order) {
        Storage.addOrder(order);
        return order;
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return Storage.orders.stream()
                .filter(order -> order.getUserId().equals(user.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Order> get(Long id) {
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

    @Override
    public Order update(Order order) {
        IntStream.range(0, Storage.orders.size())
                .filter(x -> order.getId().equals(Storage.users.get(x).getId()))
                .forEach(i -> Storage.orders.set(i, order));
        return order;
    }
}
