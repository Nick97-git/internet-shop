package mate.academy.internet.shop.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.internet.shop.model.ShoppingCart;

public interface ShoppingCartDao {

    ShoppingCart update(ShoppingCart shoppingCart);

    ShoppingCart create(ShoppingCart shoppingCart);

    Optional<ShoppingCart> get(Long id);

    List<ShoppingCart> getAll();

    boolean delete(Long id);
}
