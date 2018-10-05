package ro.teamnet.zth.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.Servlet;

@MultipartConfig
public class ZeroToHeroServlet extends HttpServlet {

    private String handleRequest (HttpServletRequest req){
        String response = "Hello <b>" + req.getParameter("firstName") + " " + req.getParameter("lastName") + "</b>! Enjoy Zero To Hero!!!";
        return response;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getWriter().write(handleRequest(req));
    }
}
