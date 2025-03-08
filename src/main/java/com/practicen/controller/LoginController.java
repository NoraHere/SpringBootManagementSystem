package com.practicen.controller;

import com.practicen.pojo.Emp;
import com.practicen.pojo.Result;
import com.practicen.service.EmpService;
import com.practicen.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        Emp e= empService.login(emp);
        if(e !=null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            claims.put("name", e.getName());

            String token = JwtUtils.generateJwt(claims);
            return Result.success(token);
        }
        return Result.error("username or password error");
//        return e!=null ? Result.success() : Result.error("username or password error");
    }
}
