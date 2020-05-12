package mate.academy.internet.shop.service.impl;

import java.util.List;
import mate.academy.internet.shop.dao.ShoppingCartDao;
import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.lib.Service;
import mate.academy.internet.shop.model.Product;
import mate.academy.internet.shop.model.ShoppingCart;
import mate.academy.internet.shop.service.ShoppingCartService;
import mate.academy.internet.shop.service.UserService;

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
