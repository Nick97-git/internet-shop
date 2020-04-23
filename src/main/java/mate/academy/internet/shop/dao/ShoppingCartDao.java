package mate.academy.internet.shop.dao;

import java.util.List;
import java.util.Optional;

import mate.academy.internet.shop.model.Product;
import mate.academy.internet.shop.model.ShoppingCart;

public interface ShoppingCartDao {

    Optional<ShoppingCart> getByUserId(Long userId);

    List<Product> getAllProducts(ShoppingCart shoppingCart);

    void update(ShoppingCart shoppingCart);

    ShoppingCart create(ShoppingCart shoppingCart);
}
