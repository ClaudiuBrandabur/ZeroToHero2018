package ro.teamnet.zth.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ZeroToHeroServlet extends HttpServlet {

    public String handleRequest(HttpServletRequest req){
        String response = "Hello " + req.getParameter("firstName") + req.getParameter("lastName") + " ! Enjoy zero to hero !!!";
        return response;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        String result = handleRequest(req);
        PrintWriter pw = resp.getWriter();
        pw.write(result);
    }
}
