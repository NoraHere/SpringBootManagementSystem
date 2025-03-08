package com.practicen.controller;

import com.practicen.pojo.Emp;
import com.practicen.pojo.PageBean;
import com.practicen.pojo.Result;
import com.practicen.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps") //optimize GetMapping
public class EmpController {
    @Autowired
    private EmpService empService;

    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("save new employee: {}",emp);
        empService.save(emp);
        return Result.success();
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        empService.delete(ids);
        return Result.success();
    }

    //Searching with condition
    @GetMapping
    public Result page(@RequestParam(defaultValue="1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("page search, param:{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        PageBean pageBean= empService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }

//    //Searching with page and pageSize
//    @GetMapping
//    public Result page(@RequestParam(defaultValue="1") Integer page,@RequestParam(defaultValue = "10") Integer pageSize){
//        log.info("page search, param:{}, {}",page,pageSize);
//        PageBean pageBean= empService.page(page,pageSize);
//        return Result.success(pageBean);
//    }
}
