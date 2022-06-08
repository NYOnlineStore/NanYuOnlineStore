package online.yukun.web.servlet;

import com.alibaba.fastjson.JSON;
import online.yukun.pojo.Order;
import online.yukun.pojo.ProductPlus;
import online.yukun.pojo.User;
import online.yukun.service.OrderService;
import online.yukun.service.ProductService;
import online.yukun.service.impl.OrderServiceImpl;
import online.yukun.service.impl.ProductServiceImpl;
import online.yukun.util.ActiveCodeUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/order/*")
public class OrderServlet extends BaseServlet{
    private final OrderService orderService = new OrderServiceImpl();
    private final ProductService productService = new ProductServiceImpl();

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 接收数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();  // json字符串
        // 2. 转成CartItem对象
        Order order = JSON.parseObject(params, Order.class);
        order.setId(ActiveCodeUtils.createActiveCode());
        for (int i = 0; i < order.getOrderItems().size(); i++) {
            order.getOrderItems().get(i).setOrderId(order.getId());
        }
        orderService.add(order);
        response.getWriter().write(order.getId());
    }

    public void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 接收数据
        String id = request.getParameter("id");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        boolean flag = orderService.selectByIdAndUserId(id, user.getId());
        response.setContentType("text/json;charset=utf-8");
        if (!flag) {
            // 用户非法查询其他用户的订单
            response.getWriter().write("failure");
        } else {
            List<ProductPlus> products = productService.selectByOrderId(id);
            String jsonString = JSON.toJSONString(products);
            // 3. 写数据
            response.getWriter().write(jsonString);
        }
    }

    public void selectById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 接收数据
        String id = request.getParameter("id");
        Order order = orderService.selectById(id);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        order.setUser(user);
        response.setContentType("text/json;charset=utf-8");
        String jsonString = JSON.toJSONString(order);
        // 3. 写数据
        response.getWriter().write(jsonString);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 接收数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();  // json字符串
        String id = request.getParameter("id");

        User user = JSON.parseObject(params, User.class);
        String name = user.getName();
        String address = user.getAddress();
        String telephone = user.getTelephone();
        orderService.update(name, address, telephone, id);
        response.getWriter().write("success");
    }

    public void pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        orderService.pay(id);
    }

    public void selectByUserId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 接收数据
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        response.setContentType("text/json;charset=utf-8");
        if (user != null) {
            int id = user.getId();
            List<Order> orders = orderService.selectByUserId(id);
            String jsonString = JSON.toJSONString(orders);
            response.getWriter().write(jsonString);
        }
    }

    public void selectBySaleId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 接收数据
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        response.setContentType("text/json;charset=utf-8");
        if (user != null) {
            int id = user.getId();
            List<Order> orders = orderService.selectBySaleId(id);
            String jsonString = JSON.toJSONString(orders);
            response.getWriter().write(jsonString);
        }
    }

    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String id = reader.readLine();
        orderService.deleteById(id);
        response.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        List<String> listIds = JSON.parseArray(params, String.class);
        String[] ids = new String[listIds.size()];
        for (int i = 0; i < listIds.size(); i++) {
            ids[i] = listIds.get(i);
        }
        orderService.deleteByIds(ids);
        response.getWriter().write("success");
    }

    public void selectPayStateById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 接收数据
        String id = request.getParameter("id");
        Order order = orderService.selectPayStateById(id);
        String jsonString = JSON.toJSONString(order);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

}
