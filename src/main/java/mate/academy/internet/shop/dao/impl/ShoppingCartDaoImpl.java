package mate.academy.internet.shop.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import mate.academy.internet.shop.dao.ShoppingCartDao;
import mate.academy.internet.shop.dao.Storage;
import mate.academy.internet.shop.lib.Dao;
import mate.academy.internet.shop.model.Product;
import mate.academy.internet.shop.model.ShoppingCart;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {

    @Override
    public Optional<ShoppingCart> getByUserId(Long userId) {
        return Storage.shoppingCarts
                .stream()
                .filter(cart -> cart.getUser().getId().equals(userId))
                .findFirst();
    }

    @Override
    public List<Product> getAllProducts(ShoppingCart shoppingCart) {
        return Storage.shoppingCarts
                .stream()
                .filter(cart -> cart.getId().equals(shoppingCart.getId()))
                .flatMap(cart -> cart.getProducts().stream())
                .collect(Collectors.toList());
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        IntStream.range(0, Storage.shoppingCarts.size())
                .filter(x -> shoppingCart.getId().equals(Storage.shoppingCarts.get(x).getId()))
                .forEach(i -> Storage.shoppingCarts.set(i, shoppingCart));
        return shoppingCart;
    }

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        boolean isExist = Storage.shoppingCarts.stream()
                .anyMatch(cart -> cart.getId().equals(shoppingCart.getId()));
        if (!isExist) {
            Storage.addShoppingCart(shoppingCart);
        }
        return shoppingCart;
    }
}
