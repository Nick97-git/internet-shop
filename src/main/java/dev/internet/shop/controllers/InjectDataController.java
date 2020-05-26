package dev.internet.shop.controllers;

import dev.internet.shop.lib.Injector;
import dev.internet.shop.model.Product;
import dev.internet.shop.model.Role;
import dev.internet.shop.model.User;
import dev.internet.shop.service.ProductService;
import dev.internet.shop.service.UserService;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectDataController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("dev.internet.shop");
    private final UserService userService = (UserService) INJECTOR.getInstance(UserService.class);
    private final ProductService productService = (ProductService) INJECTOR
            .getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User admin = new User("admin", "admin", "123");
        admin.setRoles(Set.of(Role.of("ADMIN")));
        User user = new User("alice", "alice", "123");
        user.setRoles(Set.of(Role.of("USER")));
        userService.create(admin);
        userService.create(user);
        productService.create(new Product("bread", new BigDecimal(14)));
        productService.create(new Product("milk", new BigDecimal(30)));
        productService.create(new Product("coca-cola", new BigDecimal(25)));
        req.getRequestDispatcher("/WEB-INF/views/users/injectData.jsp").forward(req, resp);
    }
}
