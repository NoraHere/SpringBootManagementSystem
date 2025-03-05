package com.practicen.service.impl;

import com.practicen.mapper.DeptMapper;
import com.practicen.pojo.Dept;
import com.practicen.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    public void add(Dept dept) {
        //finish dept data
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.insert(dept);
    }

    public void delete(Integer id){
        deptMapper.delete(id);
    }

    public List<Dept> list(){
        List<Dept> deptList = deptMapper.list();
        return deptList;
    }
}
