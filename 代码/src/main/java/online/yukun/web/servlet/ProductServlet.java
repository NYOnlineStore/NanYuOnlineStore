package online.yukun.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import online.yukun.pojo.PageBean;
import online.yukun.pojo.Product;
import online.yukun.pojo.ProductPlus;
import online.yukun.pojo.User;
import online.yukun.service.ProductService;
import online.yukun.service.UserService;
import online.yukun.service.impl.ProductServiceImpl;
import online.yukun.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/product/*")
public class ProductServlet extends BaseServlet {
    private final ProductService productService = new ProductServiceImpl();
    private final UserService userService = new UserServiceImpl();

    public void selectByRandom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PageBean<Product> pageBean=new PageBean<>();
//        //////////////////////// 如果有记录就使用之前的
//        Cookie[] cookies = request.getCookies();
//        String _cCurrentPage = "";
//        String _cPageSize = "";
//        String _cTotalCount = "";
//        String back = "";
//        for (Cookie cookie : cookies) {
//            if ("currentPage".equals(cookie.getName())) {
//                _cCurrentPage = cookie.getValue();
//            }
//            if ("pageSize".equals(cookie.getName())) {
//                _cPageSize = cookie.getValue();
//            }
//            if ("totalCount".equals(cookie.getName())) {
//                _cTotalCount = cookie.getValue();
//            }
//            if ("back".equals(cookie.getName())) {
//                back = cookie.getValue();
//                cookie.setValue("");
//                response.addCookie(cookie);
//            }
//        }
//        if (Objects.equals(back, "true")) {
//            HttpSession session = request.getSession();
//            String _products = (String) session.getAttribute("products");
//            List<Product> products = JSON.parseArray(_products, Product.class);
//            pageBean.setRows(products);
//
//            int currentPage = JSON.parseObject(_cCurrentPage, Integer.class);
//            pageBean.setCurrentPage(currentPage);
//            int pageSize = JSON.parseObject(_cPageSize, Integer.class);
//            pageBean.setCurrentPage(pageSize);
//            int totalCount = JSON.parseObject(_cTotalCount, Integer.class);
//            pageBean.setCurrentPage(totalCount);
//        }
//        /////////////////////
//        else {
        // 1. 调用service查询
        String _pageSize = request.getParameter("pageSize");
        int pageSize = Integer.parseInt(_pageSize);
        PageBean<Product> pageBean = productService.selectByRandom(pageSize);

        // 2. 转为json
        String jsonString = JSON.toJSONString(pageBean);
        // 3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    // 分页条件查询
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PageBean<Product> pageBean = new PageBean<>();
//        //////////////////////// 如果有记录就使用之前的
//        Cookie[] cookies = request.getCookies();
//        String _cCurrentPage = "";
//        String _cPageSize = "";
//        String _cTotalCount = "";
//        String back = "";
//        for (Cookie cookie : cookies) {
//            if ("currentPage".equals(cookie.getName())) {
//                _cCurrentPage = cookie.getValue();
//            }
//            if ("pageSize".equals(cookie.getName())) {
//                _cPageSize = cookie.getValue();
//            }
//            if ("totalCount".equals(cookie.getName())) {
//                _cTotalCount = cookie.getValue();
//            }
//            if ("back".equals(cookie.getName())) {
//                back = cookie.getValue();
//                cookie.setValue("");
//                response.addCookie(cookie);
//            }
//        }
//        if (Objects.equals(back, "true")) {
//            HttpSession session = request.getSession();
//            String _products = (String) session.getAttribute("products");
//            List<Product> products = JSON.parseArray(_products, Product.class);
//            pageBean.setRows(products);
//
//            int currentPage = JSON.parseObject(_cCurrentPage, Integer.class);
//            pageBean.setCurrentPage(currentPage);
//            int pageSize = JSON.parseObject(_cPageSize, Integer.class);
//            pageBean.setCurrentPage(pageSize);
//            int totalCount = JSON.parseObject(_cTotalCount, Integer.class);
//            pageBean.setCurrentPage(totalCount);
//        }
//        /////////////////////
//        else {
        // 1. 接收当前页码和每页展示条数     url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        // 1.2 获取查询条件对象
        BufferedReader reader = request.getReader();
        String params = reader.readLine();  // json字符串
        Product product = JSON.parseObject(params, Product.class);
        // 2.1 调用service查询
        PageBean<Product> pageBean = productService.selectByPageAndCondition(currentPage, pageSize, product);
        // 2.2 转为json
        String jsonString = JSON.toJSONString(pageBean);
        // 3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _id = request.getParameter("id");
        int id = Integer.parseInt(_id);

        // 2.1 调用service查询
        ProductPlus productPlus = productService.selectById(id);
        productPlus.setSalePerson(userService.selectById(productPlus.getSaleId()));
        // 2.2 转为json
        String jsonString = JSON.toJSONString(productPlus);
        // 3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    // 保存当前页面内容
    public void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String params = reader.readLine();  // json字符串
//        params = URLEncoder.encode(params, StandardCharsets.UTF_8);
        JSONObject json_test = JSONObject.parseObject(params);
//        List<Product> products = JSON.parseArray(json_test.getString("rows"), Product.class);
//        int currentPage = JSON.parseObject(json_test.getString("currentPage"), Integer.class);
//        int pageSize = JSON.parseObject(json_test.getString("pageSize"), Integer.class);
//        int totalCount = JSON.parseObject(json_test.getString("totalCount"), Integer.class);
//        Cookie cookie = new Cookie("products", URLEncoder.encode(json_test.getString("rows"), StandardCharsets.UTF_8));
//        response.addCookie(cookie);
        // 存储到Session中
        HttpSession session = request.getSession();
        session.setAttribute("products", json_test.getString("rows"));

        Cookie cookie = new Cookie("currentPage", json_test.getString("currentPage"));
        response.addCookie(cookie);
        cookie = new Cookie("pageSize", json_test.getString("pageSize"));
        response.addCookie(cookie);
        cookie = new Cookie("totalCount", json_test.getString("totalCount"));
        response.addCookie(cookie);
    }

//    // 当点击返回时，back=true
//    public void back (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Cookie cookie = new Cookie("back", "true");
//        response.addCookie(cookie);
//    }

    public void cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _id = request.getParameter("id");
        int id = Integer.parseInt(_id);
        // 2.1 调用service查询
        List<ProductPlus> products = productService.selectByUserId(id);
        // 2.2 转为json
        String jsonString = JSON.toJSONString(products);
        // 3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectBySaleId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 接收数据
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        response.setContentType("text/json;charset=utf-8");
        if (user != null) {
            int id = user.getId();
            List<ProductPlus> products = productService.selectBySaleId(id);
            String jsonString = JSON.toJSONString(products);
            response.getWriter().write(jsonString);
        }
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 接收数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();  // json字符串
        ProductPlus productPlus = JSON.parseObject(params, ProductPlus.class);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        productPlus.setSaleId(user.getId());
        // 2.1 调用service查询
        productService.add(productPlus);
        // 2.2 转为json
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write("success");
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 接收数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();  // json字符串
        Integer id = JSON.parseObject(params, Integer.class);
        // 2.1 调用service查询
        productService.delete(id);
        // 2.2 转为json
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 接收数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();  // json字符串
        // 2. 转成User对象
        List<Integer> listIds = JSON.parseArray(params, Integer.class);
        int[] ids = new int[listIds.size()];
        for (int i = 0; i < listIds.size(); i++) {
            ids[i] = listIds.get(i);
        }
        productService.deleteByIds(ids);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write("success");
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 接收数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();  // json字符串
        ProductPlus productPlus = JSON.parseObject(params, ProductPlus.class);
        // 2.1 调用service查询
        productService.update(productPlus);
        // 2.2 转为json
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write("success");
    }

    public void takeDown(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 接收数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();  // json字符串
        Integer id = JSON.parseObject(params, Integer.class);
        // 2.1 调用service查询
        productService.takeDown(id);
        // 2.2 转为json
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write("success");
    }

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 接收数据
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        response.setContentType("text/json;charset=utf-8");
        if (user != null && Objects.equals(user.getRole(), "adm")) {
            List<ProductPlus> products = productService.selectAll();
            String jsonString = JSON.toJSONString(products);
            response.getWriter().write(jsonString);
        }
    }

}
