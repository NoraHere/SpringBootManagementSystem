package com.practicen.filter;

import com.alibaba.fastjson.JSONObject;
import com.practicen.pojo.Result;
import com.practicen.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")//path filter intercepted
public class LoginCheckFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURL().toString();
        log.info("request url: {}",url);
        if(url.contains("/login")){
            chain.doFilter(request, response); //放行
            return;
        }

        String token = request.getHeader("token");
        log.info("token from request header: {}",token);

        if(!StringUtils.hasLength(token)){
            log.info("token not exist");
            Result responseResult = Result.error("NOT_LOGIN");
            String json = JSONObject.toJSONString(responseResult);
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write(json);
            return;
        }

        try{
            JwtUtils.parseJwt(token);
        }catch (Exception e){
            log.info("token parse error");
            Result responseResult = Result.error("NOT_LOGIN");
            String json = JSONObject.toJSONString(responseResult);
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write(json);
            return;
        }

        chain.doFilter(request, response);
    }
}
