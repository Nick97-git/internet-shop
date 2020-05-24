package dev.internet.shop.service.impl;

import dev.internet.shop.dao.ShoppingCartDao;
import dev.internet.shop.lib.Inject;
import dev.internet.shop.lib.Service;
import dev.internet.shop.model.Product;
import dev.internet.shop.model.ShoppingCart;
import dev.internet.shop.service.ShoppingCartService;
import dev.internet.shop.service.UserService;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Inject
    private UserService userService;

    @Override
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) {
        shoppingCart.getProducts().add(product);
        return shoppingCartDao.update(shoppingCart);
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {
        boolean isRemove = shoppingCart.getProducts().remove(product);
        shoppingCartDao.update(shoppingCart);
        return isRemove;
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.getProducts().clear();
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart getByUserId(Long userId) {
        List<ShoppingCart> shoppingCarts = shoppingCartDao.getAll();
        for (ShoppingCart shoppingCart : shoppingCarts) {
            if (shoppingCart.getUserId().equals(userId)) {
                return shoppingCart;
            }
        }
        return shoppingCartDao.create(new ShoppingCart(userId));
    }

    @Override
    public List<Product> getAllProducts(ShoppingCart shoppingCart) {
        return shoppingCartDao.get(shoppingCart.getId()).get().getProducts();
    }
}
