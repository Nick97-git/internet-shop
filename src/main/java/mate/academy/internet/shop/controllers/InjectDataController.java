package mate.academy.internet.shop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internet.shop.lib.Injector;
import mate.academy.internet.shop.model.Product;
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
        userService.create(new User("bob", "bob43", "jfkdskfd"));
        userService.create(new User("alice", "alice43", "jf323kfd"));
        productService.create(new Product("bread",14));
        productService.create(new Product("milk",30));
        productService.create(new Product("coca-cola",25));
        req.getRequestDispatcher("/WEB-INF/views/users/injectData.jsp").forward(req, resp);
    }
}
