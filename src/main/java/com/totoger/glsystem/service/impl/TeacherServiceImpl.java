package com.totoger.glsystem.service.impl;

import com.totoger.glsystem.pojo.Teacher;
import com.totoger.glsystem.dao.TeacherMapper;
import com.totoger.glsystem.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Teacher findByUserName(String username) {
        // TODO Auto-generated method stub
        return teacherMapper.findByUserName(username);
    }
    @Override
    public int add(Teacher teacher) {
        // TODO Auto-generated method stub
        return teacherMapper.add(teacher);
    }
    @Override
    public List<Teacher> findList(Map<String,Object> queryMap) {
        // TODO Auto-generated method stub
        return teacherMapper.findList(queryMap);
    }
    @Override
    public int getTotal(Map<String, Object> queryMap) {
        // TODO Auto-generated method stub
        return teacherMapper.getTotal(queryMap);
    }
    @Override
    public int edit(Teacher teacher) {
        // TODO Auto-generated method stub
        return teacherMapper.edit(teacher);
    }
    @Override
    public int delete(String ids) {
        // TODO Auto-generated method stub
        return teacherMapper.delete(ids);
    }
    


}
