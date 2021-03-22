package com.zjh.eduService.filter.badLanguageHandler;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 脏话过滤器
 */
public class BadLanguageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        RequestUtils req = new RequestUtils((HttpServletRequest) servletRequest);
        System.out.println("过滤器执行");
        filterChain.doFilter(req, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
    }
}
