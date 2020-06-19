package com.JavaEE.homework.service.impl;

import com.JavaEE.homework.entity.Homework;
import com.JavaEE.homework.mapper.HomeworkMapper;
import com.JavaEE.homework.service.HomeworkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("homeworkService")
public class HomeworkServiceImpl implements HomeworkService {

    @Resource
    private HomeworkMapper homeworkMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return homeworkMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Homework record) {
        return homeworkMapper.insert(record);
    }

    @Override
    public int insertSelective(Homework record) {
        return homeworkMapper.insertSelective(record);
    }

    @Override
    public Homework selectByPrimaryKey(Integer id) {
        return homeworkMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Homework record) {
        return homeworkMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Homework record) {
        return homeworkMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Homework> selectPaging(Integer page, Integer limit) {
        return homeworkMapper.selectPaging((page - 1) * 10, limit);
    }

    @Override
    public int tatolCount() {
        return homeworkMapper.tatolCount();
    }

    @Override
    public List<Homework> selectByCondition(String search) {
        return homeworkMapper.selectByCondition(search);
    }

    @Override
    public Homework selectById(Integer id) {
        return homeworkMapper.selectById(id);
    }
}