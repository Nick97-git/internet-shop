package mate.academy.internet.shop.service;

import java.util.List;
import mate.academy.internet.shop.exceptions.DataProcessingException;
import mate.academy.internet.shop.model.Product;
import mate.academy.internet.shop.model.ShoppingCart;

public interface ShoppingCartService {

    ShoppingCart addProduct(ShoppingCart shoppingCart, Product product)
            throws DataProcessingException;

    boolean deleteProduct(ShoppingCart shoppingCart, Product product)
            throws DataProcessingException;

    void clear(ShoppingCart shoppingCart) throws DataProcessingException;

    ShoppingCart getByUserId(Long userId) throws DataProcessingException;

    List<Product> getAllProducts(ShoppingCart shoppingCart) throws DataProcessingException;
}
