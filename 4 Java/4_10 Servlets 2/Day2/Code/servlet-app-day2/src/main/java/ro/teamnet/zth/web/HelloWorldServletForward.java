package ro.teamnet.zth.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloWorldServletForward extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String attribute = (String) request.getAttribute("testAttribute");
        response.getWriter().write("Hello <b>" + request.getParameter("user") + " " + "<b> from the Forward Servlet! "
                + attribute);
    }

}
