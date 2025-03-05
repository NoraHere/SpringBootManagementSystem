package com.practicen.controller;

import com.practicen.pojo.Dept;
import com.practicen.pojo.Result;
import com.practicen.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j //logger
@RestController
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @PostMapping()
    public Result add(@RequestBody Dept dept) {
        log.info("add dept: {}", dept);
        deptService.add(dept);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("delete dept id");
        deptService.delete(id);
        return Result.success();
    }

    @GetMapping()
    public Result list(){
        log.info("list all dept data");
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }
}
