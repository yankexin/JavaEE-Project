package com.JavaEE.homework.service;

import com.JavaEE.homework.entity.Student;

public interface StudentService {
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    Student selectByUsername(String username);
}
