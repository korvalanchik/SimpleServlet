package com.example.simpleservlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "simpleservlet", value = "/time")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ZoneId zone;
        String param = req.getParameter("timezone");
        if(param == null || param == "") {
            zone = ZoneId.of("UTC");
        } else {
            zone = ZoneId.of(param.replace(" ", "+"));
        }
        ZonedDateTime timeZone = ZonedDateTime.now(zone);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String initTime = dtf.format(timeZone);
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h2>It's now, at " + initTime + " " + zone + ", or never</h2>");
        out.println("<h3>Enter time zone from UTC-18 to UTC+18</h3>");
        out.println("<form method=\"GET\" action=\"time\"><button>GET</button><input name=\"timezone\" value=\"UTC+2\"></form>");
        out.println("</body></html>");
        resp.getWriter().close();
    }
}
