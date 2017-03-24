package com.aifeng;

import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String uri = httpRequest.getRequestURI();
        HttpSession session = httpRequest.getSession(false);

        if (uri.contains("mobile")) {
            chain.doFilter(request, response);
        } else {
            if (session == null && !uri.endsWith("toLogin.do")) {
                httpResponse.sendRedirect("/toLogin.do");
            } else if (uri.endsWith(".jpg") || uri.endsWith(".png")) {
                //TODO 静态资源不再通过 tomcat 部署时使用 nginx
                chain.doFilter(request, response);
            } else {
                if ((uri.endsWith("login.do")) && ((HttpServletRequest) request).getMethod().equals(HttpMethod.GET.toString())) {
                    httpResponse.sendRedirect("/index.do");
                } else {
                    chain.doFilter(request, response);
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}
