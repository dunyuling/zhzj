package com.aifeng;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by pro on 17-3-10.
 */
public class ZhzjFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("---");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
