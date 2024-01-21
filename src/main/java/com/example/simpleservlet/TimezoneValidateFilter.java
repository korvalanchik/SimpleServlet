package com.example.simpleservlet;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.regex.Pattern;

@WebFilter(value = "/time/*")
public class TimezoneValidateFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String timeZone = req.getParameter("timezone");
        System.out.println(timeZone);
        if ((timeZone == null) || (Pattern.matches("^UTC[+,-]([0-1]?[0-8])(?:[:][0-5]\\d)?$",timeZone))) {
            chain.doFilter(req, res);
        } else {
            res.setStatus(400);
            res.setContentType("application/json");
            res.getWriter().write("{\"Error\": \"Invalid timezone\"}");
            res.getWriter().close();
        }
    }


}
