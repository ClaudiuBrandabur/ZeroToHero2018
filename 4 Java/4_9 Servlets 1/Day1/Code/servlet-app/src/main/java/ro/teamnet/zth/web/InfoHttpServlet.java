package ro.teamnet.zth.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class InfoHttpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        Enumeration<String> headers = req.getHeaderNames();

        String table = "<table>";

        while(headers.hasMoreElements()){
            String myCurrentheader = headers.nextElement();
            table += "<tr>";
            table += "<td>" + myCurrentheader + "</td>";
            table += "</tr>";
        }

        PrintWriter pw = resp.getWriter();
        pw.write(table);
    }
}
