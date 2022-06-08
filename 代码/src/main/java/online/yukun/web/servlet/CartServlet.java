package online.yukun.web.servlet;

import com.alibaba.fastjson.JSON;
import online.yukun.pojo.CartItem;
import online.yukun.service.CartService;
import online.yukun.service.impl.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/cart/*")
public class CartServlet extends BaseServlet {
    private final CartService cartService = new CartServiceImpl();

    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 接收数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();  // json字符串
        // 2. 转成User对象
        CartItem cartItem = JSON.parseObject(params, CartItem.class);
        int delete = cartService.delete(cartItem.getUserId(), cartItem.getProductId());
        if (delete != 0) {
            response.getWriter().write("success");
        } else {
            response.getWriter().write("failure");
        }
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 接收数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();  // json字符串
        // 2. 转成CartItem对象
        CartItem cartItem = JSON.parseObject(params, CartItem.class);
        cartService.add(cartItem);
        response.getWriter().write("success");
    }
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 接收数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();  // json字符串
        // 2. 转成CartItem对象
        CartItem cartItem = JSON.parseObject(params, CartItem.class);
        cartService.update(cartItem);
        response.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 接收数据
        String _userId = request.getParameter("userId");
        int userId = Integer.parseInt(_userId);
        BufferedReader reader = request.getReader();
        String params = reader.readLine();  // json字符串
        // 2. 转成User对象
        List<Integer> listIds = JSON.parseArray(params, Integer.class);
        int[] ids = new int[listIds.size()];
        for (int i = 0; i < listIds.size(); i++) {
            ids[i] = listIds.get(i);
        }
        cartService.deleteByIds(ids, userId);
        response.getWriter().write("success");
    }

}
