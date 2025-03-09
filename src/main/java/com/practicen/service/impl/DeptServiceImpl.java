package com.practicen.service.impl;

import com.practicen.mapper.DeptMapper;
import com.practicen.mapper.EmpMapper;
import com.practicen.pojo.Dept;
import com.practicen.pojo.DeptLog;
import com.practicen.service.DeptLogService;
import com.practicen.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptLogService deptLogService;

    public void add(Dept dept) {
        //finish dept data
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.insert(dept);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id){
        try {
            deptMapper.delete(id); //根据ID删除部门数据

            int i = 1/0;
            //if(true){throw new Exception("出错啦...");}

            empMapper.delete(new ArrayList<>(id)); //根据部门ID删除该部门下的员工
        } finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("execute delete dept, id: "+id);
            deptLogService.insert(deptLog);
        }
    }

    public List<Dept> list(){
        List<Dept> deptList = deptMapper.list();
        return deptList;
    }
}
