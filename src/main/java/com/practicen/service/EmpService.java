package com.practicen.service;

import com.practicen.pojo.Emp;
import com.practicen.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

    public void update(Emp emp);

    public Emp getById(Integer id);

    void save(Emp emp);

    void delete(List<Integer> ids);

//    PageBean page(Integer page, Integer pageSize);
    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);
}
