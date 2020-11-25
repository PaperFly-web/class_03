package com.paperfly.classUtils.controller;

import com.paperfly.classUtils.utils.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j

@Order(1)
@WebFilter(filterName = "MyFilter",urlPatterns="/*")
public class MyFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        log.info("此次请求IP："+ IpUtil.getIpAddr((HttpServletRequest) req));
        chain.doFilter(req, resp);
    }
    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
