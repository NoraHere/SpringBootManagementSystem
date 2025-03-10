package com.practicen.aop;

import com.alibaba.fastjson.JSONObject;
import com.practicen.mapper.OperateLogMapper;
import com.practicen.pojo.OperateLog;
import com.practicen.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.practicen.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //obtain and parse jwt
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJwt(jwt);
        Integer operateUser = (Integer) claims.get("id");

        LocalDateTime operateTime = LocalDateTime.now();

        String className = joinPoint.getTarget().getClass().getName();

        String methodName = joinPoint.getSignature().getName();

        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        long begin = System.currentTimeMillis();

        Object result = joinPoint.proceed(); //调用原始目标方法运行
        long end = System.currentTimeMillis();

        String returnValue = JSONObject.toJSONString(result);

        Long costTime = end - begin;

        OperateLog operateLog = new OperateLog(null, operateUser, operateTime,className, methodName, methodParams, returnValue,
                costTime);
        operateLogMapper.insert(operateLog);

        log.info("aop operation log: {}",operateLog);

        return result;

    }
}
