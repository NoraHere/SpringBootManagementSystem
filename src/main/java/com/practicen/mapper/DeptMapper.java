package com.practicen.mapper;

import com.practicen.pojo.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {
    @Insert("insert into dept (name, create_time, update_time) values (#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

    @Delete("delete from dept where id = #{id}")
    void delete(Integer id);

    @Select("select id,name,create_time,update_time from dept")
    List<Dept> list();
}
