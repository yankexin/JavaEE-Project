package com.JavaEE.homework.service.impl;

import com.JavaEE.homework.entity.Teacher;
import com.JavaEE.homework.mapper.TeacherMapper;
import com.JavaEE.homework.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return teacherMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Teacher record) {
        return teacherMapper.insert(record);
    }

    @Override
    public int insertSelective(Teacher record) {
        return teacherMapper.insertSelective(record);
    }

    @Override
    public Teacher selectByPrimaryKey(Integer id) {
        return teacherMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Teacher record) {
        return teacherMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Teacher record) {
        return teacherMapper.updateByPrimaryKey(record);
    }

    @Override
    public Teacher selectByUsername(String username) {
        return teacherMapper.selectByUsername(username);
    }
}