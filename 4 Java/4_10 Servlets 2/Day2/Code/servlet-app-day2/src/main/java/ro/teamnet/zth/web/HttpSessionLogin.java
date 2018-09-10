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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username;
        String password;

        username = req.getParameter("username");
        password = req.getParameter("password");
        PrintWriter printWriter = resp.getWriter();

        if(username.equals("admin") && password.equals("admin")){
            printWriter.write("Welcome back " + username + "!");
            String sessionId = req.getRequestedSessionId();
            printWriter.write(sessionId);
        }
        else{
            req.getSession().setAttribute("username", username);
            req.getSession().setAttribute("password", password);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/loginFail.jsp");
            requestDispatcher.forward(req, resp);

        }



    }
}
