package ro.teamnet.zth.web;

import ro.teamnet.zth.file.LogFileWriter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;

public class HeadersLogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        LogFileWriter logFileWriter =  new LogFileWriter();
        LogFileWriter.logHeader("some_header", "some_value");
    }

    @Override
    public void destroy() {

    }
}
