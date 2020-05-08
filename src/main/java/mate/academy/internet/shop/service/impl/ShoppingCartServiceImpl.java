package mate.academy.internet.shop.service.impl;

import java.util.List;
import mate.academy.internet.shop.dao.ShoppingCartDao;
import mate.academy.internet.shop.exceptions.DataProcessingException;
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
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product)
            throws DataProcessingException {
        shoppingCart.getProducts().add(product);
        return shoppingCartDao.update(shoppingCart);
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product)
            throws DataProcessingException {
        boolean isRemove = shoppingCart.getProducts()
                .removeIf(prod -> prod.getId().equals(product.getId()));
        shoppingCartDao.update(shoppingCart);
        return isRemove;
    }

    @Override
    public void clear(ShoppingCart shoppingCart) throws DataProcessingException {
        shoppingCart.getProducts().clear();
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart getByUserId(Long userId) throws DataProcessingException {
        return shoppingCartDao.getAll().stream()
                .filter(shoppingCart -> shoppingCart.getUser().getId().equals(userId))
                .findFirst()
                .orElse(shoppingCartDao.create(new ShoppingCart(userService.get(userId))));
    }

    @Override
    public List<Product> getAllProducts(ShoppingCart shoppingCart) throws DataProcessingException {
        return shoppingCartDao.get(shoppingCart.getId()).get().getProducts();
    }
}
