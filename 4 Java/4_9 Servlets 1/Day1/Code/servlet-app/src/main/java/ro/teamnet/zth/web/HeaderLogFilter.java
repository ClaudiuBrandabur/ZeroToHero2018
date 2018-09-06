package ro.teamnet.zth.web;

import ro.teamnet.zth.file.LogFileWriter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;

public class HeaderLogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        ro.teamnet.zth.file.LogFileWriter logFileWriter =  new LogFileWriter();
        logFileWriter.logHeader("some_header", "some_value");
    }

    @Override
    public void destroy() {

    }
}
