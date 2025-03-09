package com.practicen.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")//path filter intercepted
public class DemoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("DemoFilter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("DemoFilter doFilter");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        System.out.println("DemoFilter destroy");
    }
}
