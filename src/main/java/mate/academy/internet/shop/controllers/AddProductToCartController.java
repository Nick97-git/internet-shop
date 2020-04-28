package mate.academy.internet.shop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internet.shop.lib.Injector;
import mate.academy.internet.shop.model.ShoppingCart;
import mate.academy.internet.shop.service.ProductService;
import mate.academy.internet.shop.service.ShoppingCartService;

public class AddProductToCartController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internet.shop");
    private final ShoppingCartService shoppingCartService = (ShoppingCartService) INJECTOR
            .getInstance(ShoppingCartService.class);
    private final ProductService productService = (ProductService) INJECTOR
            .getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String userId = req.getParameter("user_id");
        String productId = req.getParameter("product_id");
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(Long.valueOf(userId));
        shoppingCartService.addProduct(shoppingCart, productService.get(Long.valueOf(productId)));
        resp.sendRedirect(req.getContextPath() + "/shopping-cart/all");
    }
}
