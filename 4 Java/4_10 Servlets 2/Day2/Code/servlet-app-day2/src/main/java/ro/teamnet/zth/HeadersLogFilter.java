package ro.teamnet.zth;

import ro.teamnet.zth.web.HelloWorldServlet;

import ro.teamnet.zth.file.LogFileWriter;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

import static ro.teamnet.zth.file.LogFileWriter.logHeader;

public class HeadersLogFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logHeader("some header", "some value");
    }

    @Override
    public void destroy() {

    }
}
