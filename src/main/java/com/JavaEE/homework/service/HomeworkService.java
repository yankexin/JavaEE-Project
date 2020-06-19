package com.JavaEE.homework.service;

import com.JavaEE.homework.entity.Homework;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HomeworkService {
    int deleteByPrimaryKey(Integer id);

    int insert(Homework record);

    int insertSelective(Homework record);

    Homework selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Homework record);

    int updateByPrimaryKey(Homework record);

    List<Homework> selectPaging(@Param("page") Integer page,
                                @Param("limit") Integer limit);

    int tatolCount();

    List<Homework> selectByCondition(@Param("search") String search);

    Homework selectById(Integer id);
}
