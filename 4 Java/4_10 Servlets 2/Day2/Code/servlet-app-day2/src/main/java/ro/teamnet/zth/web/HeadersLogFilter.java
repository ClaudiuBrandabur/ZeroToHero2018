package ro.teamnet.zth.web;

import ro.teamnet.zth.file.LogFileWriter;

import javax.servlet.*;
import java.io.IOException;

public class HeadersLogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        LogFileWriter log = new LogFileWriter();
        log.logHeader("myheader","myvalue");
    }

    @Override
    public void destroy() {

    }
}

