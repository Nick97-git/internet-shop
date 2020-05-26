package dev.internet.shop.service.impl;

import dev.internet.shop.dao.OrderDao;
import dev.internet.shop.lib.Inject;
import dev.internet.shop.lib.Service;
import dev.internet.shop.model.Order;
import dev.internet.shop.model.Product;
import dev.internet.shop.model.User;
import dev.internet.shop.service.OrderService;
import dev.internet.shop.service.ShoppingCartService;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public Order completeOrder(List<Product> products, User user) {
        Order order = create(new Order(new ArrayList<>(products), user.getId()));
        shoppingCartService.clear(shoppingCartService.getByUserId(user.getId()));
        return order;
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return orderDao.getUserOrders(user);
    }

    @Override
    public Long getOrderAmount(Order order) {
        return order.getProducts().stream()
                .mapToLong(o -> o.getPrice().longValue())
                .sum();
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id).get();
    }

    @Override
    public Order create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public Order update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public boolean delete(Long id) {
        return orderDao.delete(id);
    }
}
