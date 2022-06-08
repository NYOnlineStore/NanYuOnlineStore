package online.yukun.web.servlet;

import online.yukun.util.CheckCodeUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/util/*")
public class UtilServlet extends BaseServlet {

    public void changeCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream os = response.getOutputStream();
        HttpSession session = request.getSession();
        String checkCode = CheckCodeUtil.outputVerifyImage(100, 40, os, session,4);
    }

}
