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
        Enumeration<String> values = null;

        String table = "<table>";
        table += "<tr>" + "<td> headers </td>" +  "<td> values </td>" + "<td> cookie </td>" + "<td> method </td>" + "<td> query </td> </tr>";


        while(headers.hasMoreElements()) {
            String myCurrentHeader = headers.nextElement();
            table += "<tr>";
            table += "<td>" + myCurrentHeader + "</td>";

            values = req.getHeaders(myCurrentHeader);
            while (values.hasMoreElements()) {
                String myCurrentValue = values.nextElement();
                table += "<th>" + myCurrentValue + "</th>";
            }

            table += "<th>" + req.getCookies()+ "</th>";
        }

        table += "<th>" + req.getMethod() + "</th>";
        table += "<th>" + req.getQueryString() + "</th>";
        table += "</tr>";
        table +="</table>";

        table += "<table>" + "<tr>" + "<td> param </td>" +  "<td> values </td> </tr>" ;

        Enumeration <String> params = req.getParameterNames();

        while(params.hasMoreElements()) {
            String myCurrentParam = params.nextElement();
            table += "<tr>";
            table += "<td>" + myCurrentParam + "</td>";
            String value = req.getParameter(myCurrentParam);
            table += "<th>" + value + "</th>";
        }

        table += " </table> ";


        PrintWriter pw = resp.getWriter();
        pw.write(table);
    }
}
