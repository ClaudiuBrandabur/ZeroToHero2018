package ro.teamnet.zth.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HttpSessionLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        String username;
        String password;

        resp.setContentType("text/html");
        username = req.getParameter("username");
        password = req.getParameter("password");
        PrintWriter pw = resp.getWriter();

        if (username.equals("admin") && password.equals("admin")) {
            pw.println("welcome back, " + username);
            String sessionId = req.getRequestedSessionId();
            pw.println(sessionId);
        } else {
            req.getSession().setAttribute("user", username);
            req.getSession().setAttribute("session", req.getSession());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/loginFail.jsp");
            requestDispatcher.include(req, resp);
        }
    }

}
