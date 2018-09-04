package ro.teamnet.zth.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpSessioLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String user = request.getParameter("username");
        String password = request.getParameter("password");
        Cookie[] cookies = request.getCookies();

        if (user.equals("admin") && password.equals("admin")) {
            response.getWriter().write("Welcome back " + user + "!");
            if (cookies.length > 0) {
                String ans = "\nCOOKIES\n";
                for (Cookie cookie : cookies) {
                    ans += cookie.getName() + " : " + cookie.getValue() + " \n ";
                }
                response.getWriter().write(ans);
            }
            else
                response.getWriter().write("\nThere are no cookies!");

            response.getWriter().write("\nSESSION ID: " + request.getRequestedSessionId() + "\n");
        }
        else {
            request.getSession().setAttribute("user",user);
            request.getSession().setAttribute("session", request.getSession());

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/loginFail.jsp");
            requestDispatcher.include(request, response);
        }


    }
}
