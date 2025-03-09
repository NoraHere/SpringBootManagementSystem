package com.practicen.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.practicen.pojo.Result;
import com.practicen.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
//@Component // hand to IOC
public class LoginCheckInterceptor implements HandlerInterceptor {

    //true 放行； false不放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");

        String token = request.getHeader("token");
        log.info("token from request header: {}",token);

        if(!StringUtils.hasLength(token)){
            log.info("token not exist");
            Result responseResult = Result.error("NOT_LOGIN");
            String json = JSONObject.toJSONString(responseResult);
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write(json);
            return false;
        }

        try{
            JwtUtils.parseJwt(token);
        }catch (Exception e){
            log.info("token parse error");
            Result responseResult = Result.error("NOT_LOGIN");
            String json = JSONObject.toJSONString(responseResult);
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write(json);
            return false;
        }

        return true; //放行
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler
            , ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        System.out.println("afterCompletipn");
    }
}
