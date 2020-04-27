package mate.academy.internet.shop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internet.shop.lib.Injector;
import mate.academy.internet.shop.model.User;
import mate.academy.internet.shop.service.UserService;

public class RegistrationController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate.academy.internet.shop");
    private final UserService userService = (UserService) injector.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("pwd");
        String repeatPassword = req.getParameter("pwd-repeat");
        String name = req.getParameter("name");
        if (password.equals(repeatPassword)) {
            userService.create(new User(name, login, password));
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            req.setAttribute("message", "Your password and repeat password are not the same!!!");
            req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
        }
    }
}
