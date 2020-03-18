package com.example.service.impl;


import com.example.dao.StudentMapper;
import com.example.pojo.Student;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapper studentMapper;
	@Override
	public int add(Student student) {
		// TODO Auto-generated method stub
		return studentMapper.add(student);
	}

	@Override
	public int edit(Student student) {
		// TODO Auto-generated method stub
		return studentMapper.edit(student);
	}

	@Override
	public int delete(String ids) {
		// TODO Auto-generated method stub
		return studentMapper.delete(ids);
	}

	@Override
	public List<Student> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return studentMapper.findList(queryMap);
	}

	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		return studentMapper.findAll();
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return studentMapper.getTotal(queryMap);
	}

	@Override
	public Student findByUserName(String username) {
		// TODO Auto-generated method stub
		return studentMapper.findByUserName(username);
	}

}
