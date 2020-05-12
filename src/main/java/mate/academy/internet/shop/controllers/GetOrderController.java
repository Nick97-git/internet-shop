package mate.academy.internet.shop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internet.shop.lib.Injector;
import mate.academy.internet.shop.model.Order;
import mate.academy.internet.shop.service.OrderService;

public class GetOrderController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internet.shop");
    private final OrderService orderService = (OrderService) INJECTOR
            .getInstance(OrderService.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String orderId = req.getParameter("order_id");
        Order myOrder = orderService.get(Long.valueOf(orderId));
        Long sum = myOrder.getProducts().stream()
                .mapToLong(order -> order.getPrice().longValue())
                .sum();
        req.setAttribute("order", myOrder);
        req.setAttribute("sum", sum);
        req.getRequestDispatcher("/WEB-INF/views/orders/getOrder.jsp").forward(req, resp);
    }
}
