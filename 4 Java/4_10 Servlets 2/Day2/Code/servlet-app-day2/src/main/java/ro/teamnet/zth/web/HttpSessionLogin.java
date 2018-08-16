package ro.teamnet.zth.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HttpSessionLogin extends HttpServlet {

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        super.doGet(req, resp);
//
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        String username = req.getParameter("username");//req.getAttribute("username").toString();
        String password = req.getParameter("password");//req.getAttribute("password").toString();
        Cookie[] cookies = req.getCookies();

        PrintWriter pw = resp.getWriter();

        if(username.equals("admin") && password.equals("admin")){

            pw.write("Welcome Back " + username + "\n");
            pw.write("session " + req.getSession() + "\n");

            for(int i = 0; i < cookies.length; i++){
                pw.write(cookies[i].getName() + ":" + cookies[i].getValue() +"\n");
            }

            if(cookies.length == 0){
                pw.write("there are no cookies!\n");
            }

            pw.write("session id : " + req.getRequestedSessionId() + "\n");

        }else {

            req.getSession().setAttribute("user",username);
            req.getSession().setAttribute("session", req.getSession());

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/loginFail.jsp");
            requestDispatcher.include(req,resp);
//            requestDispatcher.forward(req,resp);

            pw.write("\n"+"Hello " + username + "with id " + req.getSession().getId() + ". Your credentials are wrong!\n");
        }
    }
}
