package mate.academy.internet.shop.dao;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internet.shop.model.Order;
import mate.academy.internet.shop.model.Product;
import mate.academy.internet.shop.model.ShoppingCart;
import mate.academy.internet.shop.model.User;

public class Storage {
    public static List<Product> products = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    public static List<Order> orders = new ArrayList<>();
    public static List<ShoppingCart> shoppingCarts = new ArrayList<>();
    private static Long productId = 0L;
    private static Long userId = 0L;
    private static Long cartId = 0L;
    private static Long orderId = 0L;

    public static void addProduct(Product product) {
        product.setId(++productId);
        products.add(product);
    }

    public static void addUser(User user) {
        user.setId(++userId);
        users.add(user);
    }

    public static void addOrder(Order order) {
        order.setId(++orderId);
        orders.add(order);
    }

    public static void addShoppingCart(ShoppingCart shoppingCart) {
        shoppingCart.setId(++cartId);
        shoppingCarts.add(shoppingCart);
    }
}
