package com.practicen.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.practicen.mapper.EmpMapper;
import com.practicen.pojo.Emp;
import com.practicen.pojo.PageBean;
import com.practicen.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end){
        PageHelper.startPage(page,pageSize);
        List<Emp> empList = empMapper.list(name,gender,begin,end);
        PageInfo<Emp> pageInfo = new PageInfo<>(empList);
        PageBean pageBean = new PageBean(pageInfo.getTotal(), pageInfo.getList());
        return pageBean;
    }
/*
    @Override
    public PageBean page(Integer page, Integer pageSize){
        PageHelper.startPage(page,pageSize);
        List<Emp> empList = empMapper.list();
//        Page<Emp> p= (Page<Emp>) empList; //not working
        PageInfo<Emp> pageInfo = new PageInfo<>(empList);
        PageBean pageBean = new PageBean(pageInfo.getTotal(), pageInfo.getList());
//        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());

//        Long count = empMapper.count();
//        Integer start = (page - 1) * pageSize;
//        List<Emp> empList = empMapper.list(start, pageSize);
//        PageBean pageBean = new PageBean(count, empList);
        return pageBean;
    }

 */
}
