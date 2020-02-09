package com.zwl.aop.controller;

import org.springframework.web.context.ContextLoader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2020/2/7 17:28
 */
@WebServlet(name = "TestServlet", urlPatterns = "/TestServlet")
public class TestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write(String.valueOf(ContextLoader.getCurrentWebApplicationContext()));
    }
}
