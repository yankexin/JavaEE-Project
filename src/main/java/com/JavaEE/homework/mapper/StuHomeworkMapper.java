package com.JavaEE.homework.mapper;

import com.JavaEE.homework.entity.StuHomework;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StuHomeworkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StuHomework record);

    int insertSelective(StuHomework record);

    StuHomework selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StuHomework record);

    int updateByPrimaryKeyWithBLOBs(StuHomework record);

    int updateByPrimaryKey(StuHomework record);

    List<StuHomework> selectPaging(@Param("page") Integer page,
                                   @Param("limit") Integer limit,
                                   @Param("isCorrect") Boolean isCorrect);

    int tatolCount(@Param("isCorrect") Boolean isCorrect);

    StuHomework selectById(Integer id);

    List<StuHomework> selectByStudentId(Integer studentId);
}