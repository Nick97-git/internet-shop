package mate.academy.internet.shop.controllers;

import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internet.shop.lib.Injector;
import mate.academy.internet.shop.model.Product;
import mate.academy.internet.shop.model.Role;
import mate.academy.internet.shop.model.User;
import mate.academy.internet.shop.service.ProductService;
import mate.academy.internet.shop.service.UserService;

public class InjectDataController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internet.shop");
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
        User superUser = new User("super", "super", "123");
        superUser.setRoles(Set.of(Role.of("USER"), Role.of("ADMIN")));
        userService.create(admin);
        userService.create(user);
        userService.create(superUser);
        productService.create(new Product("bread",14));
        productService.create(new Product("milk",30));
        productService.create(new Product("coca-cola",25));
        req.getRequestDispatcher("/WEB-INF/views/users/injectData.jsp").forward(req, resp);
    }
}
