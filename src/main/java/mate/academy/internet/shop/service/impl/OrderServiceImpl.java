package mate.academy.internet.shop.service.impl;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internet.shop.dao.OrderDao;
import mate.academy.internet.shop.exceptions.DataProcessingException;
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
    public Order completeOrder(List<Product> products, User user) throws DataProcessingException {
        Order order = create(new Order(new ArrayList<>(products), user));
        shoppingCartService.clear(shoppingCartService.getByUserId(user.getId()));
        return order;
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return orderDao.getUserOrders(user);
    }

    @Override
    public Order get(Long id) throws DataProcessingException {
        return orderDao.get(id).get();
    }

    @Override
    public Order create(Order order) throws DataProcessingException {
        return orderDao.create(order);
    }

    @Override
    public List<Order> getAll() throws DataProcessingException {
        return orderDao.getAll();
    }

    @Override
    public Order update(Order order) throws DataProcessingException {
        return orderDao.update(order);
    }

    @Override
    public boolean delete(Long id) throws DataProcessingException {
        return orderDao.delete(id);
    }
}
