package com.JavaEE.homework.service.impl;

import com.JavaEE.homework.entity.StuHomework;
import com.JavaEE.homework.mapper.StuHomeworkMapper;
import com.JavaEE.homework.service.StuHomeworkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("stuHomeworkService")
public class StuHomeworkServiceImpl implements StuHomeworkService {

    @Resource
    private StuHomeworkMapper stuHomeworkMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return stuHomeworkMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(StuHomework record) {
        return stuHomeworkMapper.insert(record);
    }

    @Override
    public int insertSelective(StuHomework record) {
        return stuHomeworkMapper.insertSelective(record);
    }

    @Override
    public StuHomework selectByPrimaryKey(Integer id) {
        return stuHomeworkMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(StuHomework record) {
        return stuHomeworkMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(StuHomework record) {
        return stuHomeworkMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<StuHomework> selectPaging(Integer page, Integer limit, Boolean isCorrect) {
        return stuHomeworkMapper.selectPaging((page - 1) * 10, limit, isCorrect);
    }

    @Override
    public int tatolCount(Boolean isCorrect) {
        return stuHomeworkMapper.tatolCount(isCorrect);
    }

    @Override
    public StuHomework selectById(Integer id) {
        return stuHomeworkMapper.selectById(id);
    }

    @Override
    public List<StuHomework> selectByStudentId(Integer studentId) {
        return stuHomeworkMapper.selectByStudentId(studentId);
    }
}