package online.yukun.web.servlet;

import com.alibaba.fastjson.JSON;
import online.yukun.pojo.User;
import online.yukun.service.ProductService;
import online.yukun.service.UserService;
import online.yukun.service.impl.ProductServiceImpl;
import online.yukun.service.impl.UserServiceImpl;
import online.yukun.util.ActiveCodeUtils;
import online.yukun.util.MailUtil;

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

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private final ProductService productService = new ProductServiceImpl();
    private final UserService userService = new UserServiceImpl();

//    public void selectById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String _id = request.getParameter("id");
//        int id = Integer.parseInt(_id);
//
//        // 2.1 调用service查询
//        ProductPlus productPlus = productService.selectById(id);
//        productPlus.setSalePerson(userService.selectById(productPlus.getSaleId()));
//        // 2.2 转为json
//        String jsonString = JSON.toJSONString(productPlus);
//        // 3. 写数据
//        response.setContentType("text/json;charset=utf-8");
//        response.getWriter().write(jsonString);
//    }

    // 保存当前页面内容
    public void isLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 判断session中是否有user
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setId(-1);
            user.setUsername("");
            user.setName("");
        }
        // 开发模式
//        User user = new User();
//        user.setId(1);
//        user.setUsername("aaa");
//        user.setName("测试用户名");
        String jsonString = JSON.toJSONString(user);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

//    public void test(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //发送邮件
//        MailUtil mailUtil = MailUtil.getMailUtil();
//        //内容中使用html
//        mailUtil.sendEmail("2794028552@qq.com","激活","aaa");
//        System.out.println("注册成功,请到邮箱点击链接激活");
//    }

    public void selectUserByUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        // 2.1 调用service查询
        boolean flag = userService.selectByUsername(username);
        // 2.2 转为json
        String jsonString = JSON.toJSONString(flag);
        // 3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        // 1. 接收数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();  // json字符串
        // 2. 转成User对象
        User user = JSON.parseObject(params, User.class);
        user.setActiveCode(ActiveCodeUtils.createActiveCode());
        user.setState(0);
        if (user.getName().equals("")) {
            user.setName(user.getUsername());
        }
        // 3. 调用service添加
        userService.add(user);

        // 发送注册邮箱
        //发送邮件
        MailUtil mailUtil = MailUtil.getMailUtil();
        //内容中使用html
        String content = "感谢您注册网上商城用户，您的激活码为: " +
                user.getActiveCode() + "<br>点击进入<a href='http://yukun.online:8080/nanyu-onlinestore/active.html'>&nbsp;" +
                "激活页面&nbsp;</a>,激活后即可使用。<br>为保障您的账户安全，请在24小时内完成激活操作";
        mailUtil.sendEmail(user.getEmail(),"用户激活码",content);
        // 4. 相应成功的标识
        response.getWriter().write("success");
    }

    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        // 1. 接收数据
        String activeCode = request.getParameter("activeCode");
        // 3. 调用service添加
        int activate = userService.activate(activeCode);
        // 4. 相应成功的标识
        if (activate == 1) {
            response.getWriter().write("success");
        } else {
            response.getWriter().write("failure");
        }
    }

    public void checkCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        String checkCode = request.getParameter("checkCode");
        HttpSession session = request.getSession();
        String checkCodeGen = (String)session.getAttribute("checkCodeGen");

        // 比对验证码
        if (!checkCodeGen.equalsIgnoreCase(checkCode)) {
            response.getWriter().write("failure");
        }else {
            response.getWriter().write("success");
        }
    }

    public void selectByUsernameAndPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        User user = userService.selectByUsernameAndPassword(username, password);
        if (user != null) {
            // 登录成功
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            // System.out.println(session.getAttribute("user"));
            if ("true".equals(remember)) {
                Cookie c_username = new Cookie("username", username);
                Cookie c_password = new Cookie("password", password);
                c_username.setMaxAge(60 * 60 * 24 * 7);
                c_password.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(c_username);
                response.addCookie(c_password);
            }
            response.setContentType("text/json;charset=utf-8");
            String jsonString = JSON.toJSONString(user);
            response.getWriter().write(jsonString);
        } else {
            response.getWriter().write("failure");
        }
    }

    public void getUsernameAndPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String _cUsername = "";
        String _cPassword = "";
        for (Cookie cookie : cookies) {
            if ("username".equals(cookie.getName())) {
                _cUsername = cookie.getValue();
            }
            if ("password".equals(cookie.getName())) {
                _cPassword = cookie.getValue();
            }
        }
        User user = new User();
        user.setUsername("");
        user.setPassword("");
        response.setContentType("text/json;charset=utf-8");
        if (!Objects.equals(_cUsername, "") && !Objects.equals(_cPassword, "")) {
            user.setUsername(_cUsername);
            user.setPassword(_cPassword);
        }
        // 2. 转为json
        String jsonString = JSON.toJSONString(user);
        // 3. 写数据
        response.getWriter().write(jsonString);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        // 1. 接收数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();  // json字符串
        // 2. 转成User对象
        User user = JSON.parseObject(params, User.class);
        if (user.getName().equals("")) {
            user.setName(user.getUsername());
        }
        // 3. 调用service添加
        userService.update(user);
        HttpSession session = request.getSession();
        session.setAttribute("user",user);
        response.getWriter().write("success");
    }

    public void quit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        HttpSession session = request.getSession();
        session.invalidate();
        response.getWriter().write("success");
    }

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        List<User> users = userService.selectAll();
        String jsonString = JSON.toJSONString(users);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        // 1. 接收数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();  // json字符串
        // 2. 转成User对象
        Integer id = JSON.parseObject(params, Integer.class);
        // 3. 调用service添加
        userService.deleteById(id);
        response.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        // 1. 接收数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();  // json字符串
        // 2. 转成User对象
        // 2. 转成User对象
        List<Integer> listIds = JSON.parseArray(params, Integer.class);
        int[] ids = new int[listIds.size()];
        for (int i = 0; i < listIds.size(); i++) {
            ids[i] = listIds.get(i);
        }
        // 3. 调用service添加
        userService.deleteByIds(ids);
        response.getWriter().write("success");
    }

    public void freeze(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        // 1. 接收数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();  // json字符串
        // 2. 转成User对象
        Integer id = JSON.parseObject(params, Integer.class);
        // 3. 调用service添加
        userService.freeze(id);
        response.getWriter().write("success");
    }

    public void unfreeze(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        // 1. 接收数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();  // json字符串
        // 2. 转成User对象
        Integer id = JSON.parseObject(params, Integer.class);
        // 3. 调用service添加
        userService.unfreeze(id);
        response.getWriter().write("success");
    }
}
