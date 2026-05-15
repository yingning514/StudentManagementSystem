package com.student.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * 登录 Servlet
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // GET 请求：显示登录页面
        request.getRequestDispatcher("/login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        //设置编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        //获取表单参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //简单验证(实际应该查询数据库)
        if ("admin".equals(username) && "123456".equals(password)) {
            //验证成功,创建Session
            HttpSession session = request.getSession();
            //获取表单参数
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            // 重定向到学生列表
            response.sendRedirect("/student?action=list");
        } else{
            //验证失败，返回登录页面显示错误
            request.setAttribute("error","用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }
}
