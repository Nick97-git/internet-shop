package mate.academy.internet.shop;

import mate.academy.internet.shop.lib.Injector;
import mate.academy.internet.shop.model.Product;
import mate.academy.internet.shop.model.ShoppingCart;
import mate.academy.internet.shop.model.User;
import mate.academy.internet.shop.service.OrderService;
import mate.academy.internet.shop.service.ProductService;
import mate.academy.internet.shop.service.ShoppingCartService;
import mate.academy.internet.shop.service.UserService;

public class Application {
    private static final Injector injector = Injector.getInstance("mate.academy.internet.shop");

    public static void main(String[] args) {
        UserService userService = (UserService) injector.getInstance(UserService.class);
        User user = userService.create(new User("Nick", "login", "password"));
        System.out.println(user.toString());
        user.setPassword("new password");
        userService.update(user);
        System.out.println(user.toString());

        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        Product product1 = productService.create(new Product("product1", 452.0));
        Product product3 = productService.create(new Product("product3", 4.50));

        ShoppingCartService shoppingCartService =
                (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(user.getId());
        shoppingCartService.addProduct(shoppingCart, product3);
        shoppingCartService.addProduct(shoppingCart, product1);
        shoppingCartService.getAllProducts(shoppingCart)
                .forEach(prod -> System.out.println(prod.toString()));

        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);
        orderService.completeOrder(shoppingCartService.getAllProducts(shoppingCart), user);
        orderService.getUserOrders(user).forEach(order -> System.out.println(order.toString()));
    }
}
