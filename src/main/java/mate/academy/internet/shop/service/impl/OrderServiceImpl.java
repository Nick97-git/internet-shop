package mate.academy.internet.shop.service.impl;

import java.util.List;
import mate.academy.internet.shop.dao.OrderDao;
import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.lib.Service;
import mate.academy.internet.shop.model.Order;
import mate.academy.internet.shop.model.Product;
import mate.academy.internet.shop.model.User;
import mate.academy.internet.shop.service.OrderService;
import mate.academy.internet.shop.service.ShoppingCartService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public Order completeOrder(List<Product> products, User user) {
        List<Product> productList = products;
        shoppingCartService.clear(shoppingCartService.getByUserId(user.getId()));
        return orderDao.create(productList, user);
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return orderDao.getUserOrders(user);
    }

    @Override
    public Order getOrder(Long id) {
        return orderDao.getOrder(id).get();
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public boolean delete(Long id) {
        return orderDao.delete(id);
    }
}
