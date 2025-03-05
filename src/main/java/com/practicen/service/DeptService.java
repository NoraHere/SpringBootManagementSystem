package com.practicen.service;

import com.practicen.pojo.Dept;

import java.util.List;

public interface DeptService {
    void add(Dept dept);
    void delete(Integer id);
    List<Dept> list();
}
