package com.example.simpleservlet;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.time.LocalDateTime;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    private String initTime;

    @Override
    public void init() {
        initTime = LocalDateTime.now().toString();
        message = "Valerii!";
    }

//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("text/html");
//
//        // Hello
//        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
//        out.println("<h1>" + message + "</h1>");
//        out.println("</body></html>");
//        response.getWriter().close();
//    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h2>What are you doing, " + message + "</h2>");
        out.println(initTime);
        out.println("</body></html>");
        resp.getWriter().close();

    }

    @Override
    public void destroy() {
        initTime = null;
    }
}