package com.totoger.glsystem.service;


import com.totoger.glsystem.pojo.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface StudentService {
	public Student findByUserName(String username);
	public int add(Student student);
	public int edit(Student student);
	public int delete(String ids);
	public List<Student> findList(Map<String, Object> queryMap);
	public List<Student> findAll();
	public int getTotal(Map<String, Object> queryMap);
}
