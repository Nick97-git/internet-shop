package dev.internet.shop.controllers;

import dev.internet.shop.lib.Injector;
import dev.internet.shop.model.Order;
import dev.internet.shop.service.OrderService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetOrderController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("dev.internet.shop");
    private final OrderService orderService = (OrderService) INJECTOR
            .getInstance(OrderService.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String orderId = req.getParameter("order_id");
        Order myOrder = orderService.get(Long.valueOf(orderId));
        req.setAttribute("order", myOrder);
        req.setAttribute("amount", orderService.getOrderAmount(myOrder));
        req.getRequestDispatcher("/WEB-INF/views/orders/getOrder.jsp").forward(req, resp);
    }
}
