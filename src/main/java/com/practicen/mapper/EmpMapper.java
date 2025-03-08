package com.practicen.mapper;

import com.practicen.pojo.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    @Insert("insert into emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time)"
    + "values (#{username}, #{name},#{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime});")
    void insert(Emp emp);

    public void delete(List<Integer> ids);

    //use xml
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

//    //with PageHelper
//    @Select("select * from emp")
//    public List<Emp> list();

//    public List<Emp> page(Integer start, Integer pageSize);

//    @Select("select count(*) from emp")
//    public Long count();
//    @Select("select * from emp limit #{start}, #{pageSize}")
//    public List<Emp> list(Integer start, Integer pageSize);
}
