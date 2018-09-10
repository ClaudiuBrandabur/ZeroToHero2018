package ro.teamnet.zth.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloWorldServletForward extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().write("Hello <b>" + req.getParameter("user") + " " +
                "</b>"+ "from the forward servlet!" + req.getAttribute("testAttribute"));
    }
}
