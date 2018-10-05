package ro.teamnet.zth.web;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.Servlet;

public class InfoHttpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        Enumeration<String> headers = request.getHeaderNames();

        String table = "<table> <tr><th> HEADERS </th><th> VALUES </th><th> METHOD </th>" +
                "<th> QUERY STRING </th><tr>";
        Boolean ok = true;
        while (headers.hasMoreElements()) {
            String headerName = headers.nextElement();
            table += "<tr><th>" + headerName + "</th>";
            Enumeration<String> values = request.getHeaders(headerName);
            while (values.hasMoreElements()) {
                String value = values.nextElement();
                table += "<td>" + value + "</td>";
            }
            if (ok) {
                table += "<td>" + request.getMethod() + "</td>";
                table += "<td>" + request.getQueryString() + "</td></tr>";
            }
            else
                table += "</tr>";
            ok = false;
        }
        table += "</table><br><br><table><tr><th>COOKIES</th></tr>";

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            table += "<tr><td>" + cookie + "</td></tr>";
        }
        table += "</table><br><br><table><tr><th>PARAMETERS</th><th>VALUES OF PARAMETERS</th></tr>";

        Enumeration<String> parameters = request.getParameterNames();
        while (parameters.hasMoreElements()) {
            String parameter = parameters.nextElement();
            table += "<tr><th>" + parameter + "</th>";
            String name = request.getParameter(parameter);
            table += "<td>" + name + "</td></tr>";
        }
        table += "</table>";

        PrintWriter printWriter = response.getWriter();
        printWriter.write(table);
    }
}
