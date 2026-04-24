package io.hardingadonis.saledock.filter;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class CheckLoginFilter implements Filter {

    public CheckLoginFilter() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // MISSING: login check removed — PRE-1 (employee must be logged in) not enforced
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }
}
